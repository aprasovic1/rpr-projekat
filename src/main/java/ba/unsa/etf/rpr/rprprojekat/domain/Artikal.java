package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;

public class Artikal implements IDable
{
    private int artikal_id,cijena,kolicina;
    private String naziv_artikla,vrsta_artikla;

    public int getId() {
        return artikal_id;
    }

    public void setId(int artikal_id) {
        this.artikal_id = artikal_id;
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

    public String getVrsta_artikla() {
        return vrsta_artikla;
    }

    public void setVrsta_artikla(String vrsta_artikla) {
        this.vrsta_artikla = vrsta_artikla;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + artikal_id +
                ", Artikal: '" + naziv_artikla+" "+", vrsta: " +vrsta_artikla+
                ", kolicina "+kolicina+", cijena: "+cijena+'\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikal a1 = (Artikal) o;
        return artikal_id == a1.artikal_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikal_id,cijena,kolicina,naziv_artikla,vrsta_artikla);
    }

}
