package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class ArtikalDaoImpl extends AbstractDao<Artikal> implements ArtikalDao{


    public ArtikalDaoImpl(String tableName) {
        super("artikal");
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
        row.put("name", object.getNaziv_artikla());
        return row;
    }

    @Override
    public Artikal getByName(String naziv) throws myException {
        return null;
    }
}
