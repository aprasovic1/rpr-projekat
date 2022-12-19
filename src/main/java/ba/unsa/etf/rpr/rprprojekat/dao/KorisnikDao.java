package ba.unsa.etf.rpr.rprprojekat.dao;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

public interface KorisnikDao extends Dao<Korisnik>{

    Korisnik getByName(String ime,String prezime) throws myException;
    Korisnik getByUsername(String username) throws myException;

}
