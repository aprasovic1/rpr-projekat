package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.IDable;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends IDable> implements Dao{
    private Connection conn;
    private String tableName;

    public AbstractDao() {
         try {
             conn= GetConnection.DajConnection();
         } catch (IOException e) {
            e.printStackTrace();
         }
     }
    public abstract T row2object(ResultSet rs) throws myException;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws myException {
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
        try {
            PreparedStatement s = this.conn.prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new myException("Object not found");
            }
        } catch (SQLException e) {
            throw new myException(e.getMessage(), e);
        }
    }


    public List<T> getAll() throws myException {
        String query = "SELECT * FROM "+ tableName;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement s = this.conn.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }


    public void delete(int id) throws myException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement s = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, id);
            s.executeUpdate();
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }


    public T add(T item) throws myException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = this.conn.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }










}
