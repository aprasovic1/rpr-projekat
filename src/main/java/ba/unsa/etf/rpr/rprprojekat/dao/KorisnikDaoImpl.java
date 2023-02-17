package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class KorisnikDaoImpl extends AbstractDao<Korisnik> implements KorisnikDao{
    Properties p= new Properties();
    public KorisnikDaoImpl() {
        super("korisnik");

        try {
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Korisnik rowToObject(ResultSet rs) throws myException {
        try {
            Korisnik k = new Korisnik();
            k.setId(rs.getInt("id"));
            k.setIme(rs.getString("ime"));
            k.setPrezime(rs.getString("prezime"));
            k.setUser(rs.getString("user"));
            k.setPass(rs.getString("pass"));
            k.setJesteAdmin(rs.getBoolean("jesteAdmin"));

            return k;
        } catch (Exception e) {
            throw new myException("rowtoobject korisnikdaoimpl"+e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Korisnik object) throws myException {
        try {
            Map<String, Object> row = new TreeMap<String, Object>();
            row.put("id", object.getId());
            row.put("ime", object.getIme());
            row.put("prezime", object.getPrezime());
            row.put("user", object.getUser());
            row.put("pass", object.getPass());
            row.put("jesteAdmin", object.isJesteAdmin());
            return row;
        }
        catch (Exception e){
            throw new myException("objecttorow korisnikdaoimpl"+e.getMessage(), e);
        }
    }


    @Override
    public Korisnik getByName(String ime, String prezime) throws myException {
    Korisnik k = null;

        String query = "SELECT * FROM "+p.getProperty("db.schema")+".korisnik k WHERE concat(k.ime,k.prezime) LIKE concat(?,?)";
        try {
            Connection c=GetConnection.DajConnection();
            System.out.println(c.isValid(10));//connection valid?
           PreparedStatement s = c.prepareStatement(query);
    s.setString(1, ime);
            s.setString(2, prezime);

            System.out.println(s.toString());
            ResultSet rs = s.executeQuery();
            rs.next();
        k=rowToObject(rs);
            return k;
        } catch (Exception e) {
            throw new myException("getbyname korisnikdaoimpl"+e.getMessage(), e);

        }
    }

    @Override
    public Korisnik getByUsername(String username) throws myException{
        Korisnik k = null;

        String query = "SELECT * FROM "+p.getProperty("db.schema")+".korisnik k WHERE user LIKE ?";
        try {
            Connection c=GetConnection.DajConnection();
            System.out.println(c.isValid(10));//connection valid?
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, username);

            System.out.println("KorisnikDAOImpl:"+s.toString());
            ResultSet rs = s.executeQuery();
            rs.next();
            k=rowToObject(rs);
            return k;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);

        }
    }
}
