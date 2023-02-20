package ba.unsa.etf.rpr.rprprojekat.domain;
/**
 * Interface that forces all POJO beans to have ID field.
 */
public interface IDable {

    void setId(int id);

    int getId();
}
