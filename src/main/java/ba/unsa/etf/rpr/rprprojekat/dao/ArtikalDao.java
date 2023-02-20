package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.collections.ObservableList;
/**
 * Dao interface for Artikal domain bean
 */
public interface ArtikalDao extends Dao<Artikal>{
    ObservableList<Artikal> getByName(String naziv) throws myException;
}
