package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.GetConnection;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class KorisnikDaoImpl extends AbstractDao<Korisnik> implements KorisnikDao{
    public KorisnikDaoImpl() {
        super("korisnik");
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
            System.out.println("rowtoobject korisnikdaoimpl");
            throw new myException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Korisnik object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getIme());
        return row;
    }


    @Override
    public Korisnik getByName(String ime, String prezime) throws myException {
    Korisnik k = null;

        String query = "SELECT * FROM sql7582884.korisnik k WHERE concat(k.ime,k.prezime) LIKE concat(?,?)";
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
            System.out.println("getbyname korisnikdaoimpl");
            throw new myException(e.getMessage(), e);
        }


    }
}
