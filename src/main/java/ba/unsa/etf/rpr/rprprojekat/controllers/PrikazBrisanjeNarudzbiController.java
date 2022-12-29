package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaNarudzbeDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class PrikazBrisanjeNarudzbiController implements Initializable {
    public TableView<Narudzba> narudzbeTable;
    public TableColumn<Integer, Narudzba> narudzbaIdKolona;
    public TableColumn<Integer, Narudzba> korisnikIdKolona;
    public TableColumn<Date, Narudzba> datumNarudzbeIdKolona;
    public MenuButton menuButton;
    public TextField idNarudzbeText;
    public Button brisiNardzbuButton;

    NarudzbaDaoImpl n = new NarudzbaDaoImpl();
    private NarudzbaModel nModel=new NarudzbaModel();
    public PrikazBrisanjeNarudzbiController() {
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
    }

    public void onBrisiNarzdzbuButtonPressed(ActionEvent actionEvent) throws myException {
        idNarudzbeText.textProperty().bindBidirectional((Property<String>) nModel.id.asString());
        new StavkaNarudzbeDaoImpl().delete(nModel.id.getValue());
        n.delete(nModel.id.getValue());
        idNarudzbeText.clear();
        narudzbeTable.refresh();

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

