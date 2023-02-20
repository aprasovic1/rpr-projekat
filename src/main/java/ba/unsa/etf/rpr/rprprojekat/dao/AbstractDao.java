package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.IDable;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements CRUD methods
 */
public abstract class AbstractDao<T extends IDable> implements Dao<T>{
    private Connection conn;
    private String tableName;

    public AbstractDao(String tableName) {
         try {
             this.tableName=tableName;
             conn= GetConnection.DajConnection();
         } catch (Exception e) {
            e.printStackTrace();
         }
     }
    public abstract T rowToObject(ResultSet rs) throws myException;

    public abstract Map<String, Object> objectToRow(T object) throws myException;

    public T getById(int id) throws myException {
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
        try {
            PreparedStatement s = this.conn.prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = rowToObject(rs);
                rs.close();
                return result;
            } else {
                throw new myException("Object not found");
            }
        } catch (SQLException e) {
            throw new myException(e.getMessage(), e);
        }
    }




    public ObservableList<T> getAll() throws myException {
        String query = "SELECT * FROM "+ tableName;
        ArrayList<T> results = new ArrayList<T>();
        try{
            PreparedStatement s = this.conn.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = rowToObject(rs);
                results.add(object);
            }
            rs.close();
            System.out.println(tableName+"AbstractDAO: "+s.toString());
            return FXCollections.observableList(results);
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
            System.out.println(s.toString());
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }


    public void add(T item) throws myException{
        Map<String, Object> row = objectToRow(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement s = this.conn.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                s.setObject(counter, entry.getValue());
                counter++;
            }
            s.executeUpdate();

            ResultSet rs = s.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));


        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }
    public T update(T item) throws myException{
        Map<String, Object> row = objectToRow(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement s = this.conn.prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                s.setObject(counter, entry.getValue());
                counter++;
            }

            s.setObject(counter, item.getId());
            s.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        }
    }

    protected Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int i = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            i++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != i) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    protected String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int i = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            i++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != i) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}



