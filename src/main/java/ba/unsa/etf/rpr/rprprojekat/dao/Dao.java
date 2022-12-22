package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.collections.ObservableList;

public interface Dao<T> {

    T getById(int id)throws myException;
    T add(T item)throws myException;
    T update(T item)throws myException;
    void delete(int id)throws myException;
    ObservableList<T> getAll()throws myException;
}
