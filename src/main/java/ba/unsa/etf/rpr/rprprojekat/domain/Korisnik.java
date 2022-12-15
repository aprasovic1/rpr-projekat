package ba.unsa.etf.rpr.rprprojekat.domain;

import java.util.Objects;

public class Korisnik implements IDable {
    int korisnik_id;
String ime,prezime,telefon,user,pass;
boolean jesteAdmin;

    @Override
    public int getId() {
        return korisnik_id;
    }

    @Override
    public void setId(int id) {
        this.korisnik_id = id;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
        return "Category{" +
                "id=" + korisnik_id +
                ", Ime i prezime='" + ime+" "+prezime+" "+", telefon: " +telefon+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik k1 = (Korisnik) o;
        return korisnik_id == k1.korisnik_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnik_id, ime,prezime,user,pass,jesteAdmin);
    }
}
