package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class NarudzbaDaoImpl extends AbstractDao<Narudzba> implements NarudzbaDao {
    private Connection conn;
    private String tableName = "narudzba";

    public NarudzbaDaoImpl() {
        super("narudzba");
    }

    @Override
    public Narudzba rowToObject(ResultSet rs) throws myException {
        try {
            Narudzba n = new Narudzba();
            n.setId(rs.getInt("id"));
            n.setDatum_narudzbe(rs.getDate("datum_narudzbe").toLocalDate());
            n.setKorisnik_id(rs.getInt("korisnik_id"));
            return n;
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Narudzba object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("datum_narudzbe", object.getDatum_narudzbe());
        row.put("korisnik_id", object.getKorisnik_id()
        );
        return row;
    }

    public void add(Narudzba nar, StavkaNarudzbe sn) throws myException {
        System.out.println("ideNarudzbaAdd");
       new NarudzbaDaoImpl().add(nar);
        System.out.println("ID Narudzbe: "+nar.getId());
        sn.setNarudzba_id(nar.getId());
        System.out.println("ideStavkaNarudzbaAdd");
        new StavkaNarudzbeDaoImpl().add(sn);

    }
}