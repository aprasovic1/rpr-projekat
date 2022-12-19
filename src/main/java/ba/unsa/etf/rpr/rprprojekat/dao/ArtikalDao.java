package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

public interface ArtikalDao extends Dao<Artikal>{
    Artikal getByName(String naziv) throws myException;
}
