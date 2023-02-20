package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
/**
 * Implementation of Dao interface for StavkaNarudzbe domain bean
 */
public class StavkaNarudzbeDaoImpl extends AbstractDao<StavkaNarudzbe> implements StavkaNarudzbeDao{
    Properties p= new Properties();
    public StavkaNarudzbeDaoImpl() {
        super("stavka_narudzbe");
        try {
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public StavkaNarudzbe rowToObject(ResultSet rs) throws myException {
        try {
            StavkaNarudzbe s = new StavkaNarudzbe();
            s.setId(rs.getInt("id"));
            s.setNarudzba_id(rs.getInt("narudzba_id"));
            s.setArtikal_id(rs.getInt("artikal_id"));
            s.setKolicina(rs.getInt("kolicina"));


            return s;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(StavkaNarudzbe object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("narudzba_id", object.getNarudzba_id());
        row.put("artikal_id", object.getArtikal_id());
        row.put("kolicina", object.getKolicina());
        return row;
    }

    public void deleteWithNarudzbaID(int id) throws myException {
        String sql = "DELETE FROM "+p.getProperty("db.schema")+".stavka_narudzbe WHERE narudzba_id = ?";
        try{
            Connection c= GetConnection.DajConnection();
            PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, id);
            s.executeUpdate();
            System.out.println(s.toString());
        }catch (Exception e){
            throw new myException(e.getMessage(), e);
        }
    }
    public void add(Narudzba nar, ArrayList<StavkaNarudzbe> sn) throws myException {

        new NarudzbaDaoImpl().add(nar);
        int idNar= nar.getId();


        for (StavkaNarudzbe stavka:sn) {
            stavka.setNarudzba_id(idNar);
            new StavkaNarudzbeDaoImpl().add(stavka);
        }


    }
}
