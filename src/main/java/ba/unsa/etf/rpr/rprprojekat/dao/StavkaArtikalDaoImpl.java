package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaArtikal;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
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
import java.util.TreeMap;
/**
 * Implementation of Dao interface for StavkaArtikal domain bean
 */
public class StavkaArtikalDaoImpl  implements StavkaArtikalDao{

    public StavkaArtikalDaoImpl() {

    }

    public ObservableList<StavkaArtikal> getAllWithNarudzbaId(int narudzba_id) throws myException {
        String query = "select s.id,s.artikal_id,s.kolicina,a.cijena from stavka_narudzbe s ,artikal a where s.artikal_id= a.id and narudzba_id=?";
        ArrayList<StavkaArtikal> results = new ArrayList<StavkaArtikal>();
        try{
            Connection conn= GetConnection.DajConnection();
            PreparedStatement s = conn.prepareStatement(query);
            s.setInt(1,narudzba_id);
            ResultSet rs = s.executeQuery();
            System.out.println("getAllWithNarudzbaId :1");
            while (rs.next()){ // result set is iterator.
                StavkaArtikal object = rowToObject(rs);
                results.add(object);
            }
            System.out.println("getAllWithNarudzbaId :2");
            rs.close();
            System.out.println("StavkaArtikalDAO: "+s.toString());
            System.out.println("getAllWithNarudzbaId :3");
            return FXCollections.observableList(results);
        }catch (SQLException e){
            throw new myException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StavkaArtikal rowToObject(ResultSet rs) throws myException {
        try {
            StavkaArtikal s = new StavkaArtikal();
            s.setId(rs.getInt("id"));
            s.setArtikal_id(rs.getInt("artikal_id"));
            s.setKolicina(rs.getInt("kolicina"));
            s.setCijena(rs.getInt("cijena"));



            return s;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }


    public Map<String, Object> objectToRow(StavkaArtikal object) throws myException {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("artikal_id", object.getArtikal_id());
        row.put("kolicina", object.getKolicina());
        row.put("cijena", object.getCijena());
        return row;
    }

    @Override
    public StavkaArtikal getById(int id) throws myException {
        return null;
    }

    @Override
    public void add(StavkaArtikal item) throws myException {

    }

    @Override
    public StavkaArtikal update(StavkaArtikal item) throws myException {
        return null;
    }

    @Override
    public void delete(int id) throws myException {

    }

    @Override
    public ObservableList<StavkaArtikal> getAll() throws myException {
        return null;
    }
}
