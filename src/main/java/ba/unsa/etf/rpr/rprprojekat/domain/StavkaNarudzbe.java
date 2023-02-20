package ba.unsa.etf.rpr.rprprojekat.domain;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaNarudzbeDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.util.Objects;

public class StavkaNarudzbe implements
        IDable{
    int id, narudzba_id,artikal_id,kolicina;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbe that = (StavkaNarudzbe) o;
        return id == that.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, narudzba_id, artikal_id, kolicina);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbe{" +
                "id=" + id +
                ", narudzba_id=" + narudzba_id +
                ", artikal_id=" + artikal_id +
                ", kolicina=" + kolicina +
                '}';
    }


}
