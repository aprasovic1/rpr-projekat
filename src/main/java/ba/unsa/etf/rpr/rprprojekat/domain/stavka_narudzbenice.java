package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;

public class stavka_narudzbenice implements
        IDable{
    int id,narudzbenica_id,artikal_id,kolicina;

    public int getId() {
        return id;
    }

    public void setId(int stavka_id) {
        this.id = stavka_id;
    }

    public int getNarudzbenica_id() {
        return narudzbenica_id;
    }

    public void setNarudzbenica_id(int narudzbenica_id) {
        this.narudzbenica_id = narudzbenica_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        stavka_narudzbenice that = (stavka_narudzbenice) o;
        return id == that.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, narudzbenica_id, artikal_id, kolicina);
    }

    @Override
    public String toString() {
        return "stavka_narudzbenice{" +
                "stavka_id=" + id +
                ", narudzbenica_id=" + narudzbenica_id +
                ", artikal_id=" + artikal_id +
                ", kolicina=" + kolicina +
                '}';
    }

}
