package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;

/**
 *Bean for Artikal
 */
public class Artikal implements IDable
{
    private int id,cijena,kolicina;
    private String naziv_artikla;

    public int getId() {
        return id;
    }

    public void setId(int artikal_id) {
        this.id = artikal_id;
    }

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getNaziv_artikla() {
        return naziv_artikla;
    }

    public void setNaziv_artikla(String naziv_artikla) {
        this.naziv_artikla = naziv_artikla;
    }


    @Override
    public String toString() {
        return "Artikal:{" +
                "id=" + id +
                ", Artikal: '" + naziv_artikla+" "+
                ", kolicina "+kolicina+", cijena: "+cijena+'\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikal a1 = (Artikal) o;
        return id == a1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,cijena,kolicina,naziv_artikla);
    }

}
