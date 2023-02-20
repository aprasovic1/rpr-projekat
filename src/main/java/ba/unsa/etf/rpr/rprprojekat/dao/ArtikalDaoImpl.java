package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class ArtikalDaoImpl extends AbstractDao<Artikal> implements ArtikalDao{

    Properties p= new Properties();
    public ArtikalDaoImpl() {
        super("artikal");

        try {
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Artikal rowToObject(ResultSet rs) throws myException {
        try {
            Artikal a = new Artikal();
            a.setId(rs.getInt("id"));
           a.setNaziv_artikla(rs.getString("naziv_artikla"));
            a.setCijena(rs.getInt("cijena"));
            a.setKolicina(rs.getInt("kolicina"));
            return a;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Artikal object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("naziv_artikla", object.getNaziv_artikla());
        row.put("cijena", object.getCijena());
        row.put("kolicina", object.getKolicina());
        return row;
    }

    public void skiniSaStanja(int id,int kolicina) throws myException {
        String query ="update "+p.getProperty("db.schema")+".artikal set kolicina=kolicina-? where id=?";
        try {
            Connection c = GetConnection.DajConnection();
            System.out.println(c.isValid(10));//connection valid?
            PreparedStatement s = c.prepareStatement(query);
            s.setInt(1, kolicina);
            s.setInt(2, id);
            s.executeQuery();
        }
        catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }
    @Override
    public ObservableList<Artikal> getByName(String naziv) throws myException {
        Artikal a = null;

        String query = "SELECT * FROM "+p.getProperty("db.schema")+".artikal a WHERE a.naziv_artikla LIKE ?";
        try {
            Connection c= GetConnection.DajConnection();
            System.out.println(c.isValid(10));//connection valid?
            /*PreparedStatement s = c.prepareStatement(query);
            s.setString(1, "%"+naziv+"%");
            System.out.println(s.toString());
            ResultSet rs = s.executeQuery();
            rs.next();
            a=rowToObject(rs);
            return a;*/
            ArrayList<Artikal> results = new ArrayList<Artikal>();
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, "%"+naziv+"%");
            ResultSet rs = s.executeQuery();
            while (rs.next()){ // result set is iterator.
                Artikal object = rowToObject(rs);
                results.add(object);
            }
            rs.close();
            return FXCollections.observableList(results);
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);

        }
    }
}
