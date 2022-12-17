package ba.unsa.etf.rpr.rprprojekat.dao;

import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.util.List;

public interface Dao<T> {

    T getById(int id)throws myException;
    T add(T item)throws myException;
    T update(T item)throws myException;
    void delete(int id)throws myException;
    List<T> getAll()throws myException;
}
