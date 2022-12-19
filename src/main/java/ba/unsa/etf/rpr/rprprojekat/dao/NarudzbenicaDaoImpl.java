package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzbenica;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class NarudzbenicaDaoImpl extends AbstractDao<Narudzbenica> implements NarudzbenicaDao {
    public NarudzbenicaDaoImpl(String tableName) {
        super("narudzbenica");
    }

    @Override
    public Narudzbenica rowToObject(ResultSet rs) throws myException {
        try {
            Narudzbenica n = new Narudzbenica();
            n.setId(rs.getInt("id"));
            n.setDatum_narudzbe(rs.getDate("datum_narudzbe"));
            n.setKorisnik_id(rs.getInt( "korisnik_id") );
            return n;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Narudzbenica object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("datum_narudzbe", object.getDatum_narudzbe());
        row.put("korisnik_id", object.getKorisnik_id()
        );
        return row;
    }
}
