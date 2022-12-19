package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class StavkaNarudzbeDaoImpl extends AbstractDao<StavkaNarudzbe> implements StavkaNarudzbeDao{
    public StavkaNarudzbeDaoImpl() {
        super("stavka_narudzbe");
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
}
