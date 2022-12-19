package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class PrikazBrisanjeNarudzbiController {
    public TableView<Narudzba> narudzbeTable;
    public TableColumn<Narudzba, Integer> narudzbaIdKolona;
    public TableColumn<Narudzba, Integer> korisnikIdKolona;
    public TableColumn<Narudzba, Date> datumNarudzbeIdKolona;
    public MenuButton menuButton;
    public TextField idNarudzbeText;
    public Button brisiNardzbuButton;


    public PrikazBrisanjeNarudzbiController() throws myException {
        try {
            NarudzbaDaoImpl n = new NarudzbaDaoImpl();
            ObservableList<Narudzba> lista = FXCollections.observableArrayList(n.getAll());

            narudzbeTable = new TableView<>();

            narudzbaIdKolona = new TableColumn<>("ID");
            korisnikIdKolona = new TableColumn<>("Korisnik ID");
            datumNarudzbeIdKolona = new TableColumn<>("datumNarudzbe");

            narudzbaIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
            korisnikIdKolona.setCellValueFactory(new PropertyValueFactory<>("korisnik_id"));
            datumNarudzbeIdKolona.setCellValueFactory(new PropertyValueFactory<>("datum_narudzbe"));

            narudzbeTable.setItems(lista);


            narudzbeTable.getColumns().setAll(narudzbaIdKolona, korisnikIdKolona, datumNarudzbeIdKolona);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @FXML
    public void Initialize() {
    }

    public void onBrisiNarzdzbuButtonPressed(ActionEvent actionEvent) {
        Narudzba n;

    }
}
