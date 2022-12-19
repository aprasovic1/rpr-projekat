package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Date;
import java.util.Objects;

public class Narudzba implements IDable
{
    private int id,korisnik_id;
    private Date datum_narudzbe;

    public int getId() {
        return id;
    }

    public void setId(int narudzba_id) {
        this.id = narudzba_id;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Date getDatum_narudzbe() {
        return datum_narudzbe;
    }

    public void setDatum_narudzbe(Date datum_narudzbe) {
        this.datum_narudzbe = datum_narudzbe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzba n = (Narudzba) o;
        return id == n.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnik_id, datum_narudzbe);
    }

    @Override
    public String

    toString() {
        return "Narudzba{" +
                "narudzba_id=" + id +
                ", korisnik_id=" + korisnik_id +
                ", datum_narudzbe=" + datum_narudzbe +
                '}';
    }
}