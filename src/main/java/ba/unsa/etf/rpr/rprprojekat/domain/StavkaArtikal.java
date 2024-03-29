package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;
/**
 *Bean for Stavka and Artikal together
 * Used in PrikazNarudzbiStavkiController class
 */
public class StavkaArtikal implements IDable {
    int id, narudzba_id,artikal_id,kolicina,cijena;

    public int getId() {
        return id;
    }
    public void setId(int stavka_id) {
        this.id = stavka_id;
    }
    public int getNarudzba_id() {
        return narudzba_id;
    }
    public void setNarudzba_id(int narudzba_id) {
        this.narudzba_id = narudzba_id;
    }
    public int getArtikal_id() {
        return artikal_id;
    }
    public void setArtikal_id(int artikal_id) {
        this.artikal_id = artikal_id;
    }
    public int getKolicina() {
        return kolicina;
    }
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    public int getCijena() {
        return cijena;
    }
    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaArtikal that = (StavkaArtikal) o;
        return id == that.id && narudzba_id == that.narudzba_id && artikal_id == that.artikal_id && kolicina == that.kolicina && cijena == that.cijena;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, narudzba_id, artikal_id, kolicina, cijena);
    }

    @Override
    public String toString() {
        return "StavkaArtikal{" +
                "id=" + id +
                ", narudzba_id=" + narudzba_id +
                ", artikal_id=" + artikal_id +
                ", kolicina=" + kolicina +
                ", cijena=" + cijena +
                '}';
    }
}
