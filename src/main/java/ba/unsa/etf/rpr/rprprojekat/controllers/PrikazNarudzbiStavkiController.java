package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.ArtikalDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaArtikalDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaNarudzbeDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaArtikal;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

public class PrikazNarudzbiStavkiController implements Initializable {
    public TableView<Narudzba> narudzbeTable;
    public TableColumn<Integer, Narudzba> narudzbaIdKolona;
    public TableColumn<Integer, Narudzba> korisnikIdKolona;
    public TableColumn<Date, Narudzba> datumNarudzbeIdKolona;
    public TableView<StavkaArtikal> StavkeNarudzbeTable;
    public TableColumn<Integer, Narudzba> stavkaNarudzbeIdKolona;
    public TableColumn<Integer, Narudzba> artikalIdKolona;
    public TableColumn<Integer, Narudzba> kolicinaKolona;
    public TableColumn<Integer, Narudzba> cijenaKolona;
    public MenuButton menuButton;
    public TextField idNarudzbeText;
    public Button prikaziStavkeNarudzbeButton;
    private NarudzbaModel nModel=new NarudzbaModel();
    NarudzbaDaoImpl n = new NarudzbaDaoImpl();
    StavkaNarudzbeDaoImpl s = new StavkaNarudzbeDaoImpl();
    ArtikalDaoImpl a=new ArtikalDaoImpl();
    StavkaArtikalDaoImpl sa=new StavkaArtikalDaoImpl();

    public PrikazNarudzbiStavkiController() {

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        narudzbaIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        korisnikIdKolona.setCellValueFactory(new PropertyValueFactory<>("korisnik_id"));
        datumNarudzbeIdKolona.setCellValueFactory(new PropertyValueFactory<>("datum_narudzbe"));
        try {
            for (Narudzba nx : n.getAll()) {
                System.out.println(nx.toString());
            }
            narudzbeTable.getItems().clear();
            narudzbeTable.setItems(n.getAll());
            narudzbeTable.refresh();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        stavkaNarudzbeIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        artikalIdKolona.setCellValueFactory(new PropertyValueFactory<>("artikal_id"));
        System.out.println("Button :5");
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        System.out.println("Button :6");
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
    }
        public void onPrikaziStavkeNarudzbeButtonPressed(ActionEvent actionEvent) throws myException {

        StavkeNarudzbeTable.getItems().clear();
        System.out.println("Button :7");
        StavkeNarudzbeTable.setItems(sa.getAllWithNarudzbaId(Integer.parseInt(idNarudzbeText.textProperty().getValue())));
        System.out.println("Button :8");
        StavkeNarudzbeTable.refresh();
        System.out.println("Button :9");

    }

    public void onUpravljanjeKupcimaPressed(ActionEvent actionEvent) {
    }

    public void onUpravljanjeArtiklimaPressed(ActionEvent actionEvent) {
    }

    public class ArtikalModel {
        public SimpleIntegerProperty id = new SimpleIntegerProperty(),
                cijena = new SimpleIntegerProperty(),
                kolicina = new SimpleIntegerProperty();
        public SimpleStringProperty naziv = new SimpleStringProperty();

        public void fromArtikal(Artikal a) {
            this.id.set(a.getId());
            this.cijena.set(a.getCijena());
            this.kolicina.set(a.getCijena());
            this.naziv.set(a.getNaziv_artikla());
        }

        public Artikal toArtikal() {
            Artikal a = new Artikal();
            a.setId(this.id.getValue());
            a.setCijena(this.cijena.getValue());
            a.setKolicina(this.kolicina.getValue());
            a.setNaziv_artikla(this.naziv.getValue());
            return a;
        }

    }
    public class NarudzbaModel {
        public SimpleIntegerProperty id = new SimpleIntegerProperty(), korisnik_id = new SimpleIntegerProperty();
        public SimpleObjectProperty<LocalDate> datum_narudzbe = new SimpleObjectProperty<LocalDate>();

        public void fromNarudzba(Narudzba n) {
            this.id.set(n.getId());
            this.korisnik_id.set(n.getKorisnik_id());
            this.datum_narudzbe.set(n.getDatum_narudzbe());
        }

        public Narudzba toNarudzba() {
            Narudzba n = new Narudzba();
            n.setId(this.id.getValue());
            n.setKorisnik_id(this.korisnik_id.getValue());
            n.setDatum_narudzbe(datum_narudzbe.getValue());
            return n;
        }

    }
}
