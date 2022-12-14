package ba.unsa.etf.rpr.rprprojekat.dao;

import java.util.List;

public interface Dao<T> {

    boolean add(T t);

    T read(T t);

    boolean update(T t, String[] params);

    boolean delete(T t);
}
