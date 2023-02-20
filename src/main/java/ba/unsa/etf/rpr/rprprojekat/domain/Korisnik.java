package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;

/**
 *Bean for Korisnik
 */
public class Korisnik implements IDable {
    private int id;
    private String ime,prezime,user,pass;
    private boolean jesteAdmin;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isJesteAdmin() {
        return jesteAdmin;
    }

    public void setJesteAdmin(boolean jesteAdmin) {
        this.jesteAdmin = jesteAdmin;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", Ime i prezime='" + ime+" "+prezime+" "+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik k1 = (Korisnik) o;
        return id == k1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime,prezime,user,pass,jesteAdmin);
    }
}
