package ba.unsa.etf.rpr.rprprojekat.dao;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;

public interface KorisnikDao extends Dao<Korisnik>{

    Korisnik getByName(String ime,String prezime);

}
