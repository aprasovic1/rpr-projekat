package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class PrikazBrisanjeNarudzbiController  {
    public TableView<Narudzba> narudzbeTable;
    public TableColumn<Integer,Narudzba> narudzbaIdKolona;
    public TableColumn<Integer,Narudzba> korisnikIdKolona;
    public TableColumn<Date,Narudzba> datumNarudzbeIdKolona;
    public MenuButton menuButton;
    public TextField idNarudzbeText;
    public Button brisiNardzbuButton;

    NarudzbaDaoImpl n = new NarudzbaDaoImpl();
    public PrikazBrisanjeNarudzbiController()  {}

    @FXML
    public void initialize() throws myException {
        narudzbeTable=new TableView<Narudzba>();
        narudzbaIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        korisnikIdKolona.setCellValueFactory(new PropertyValueFactory<>("korisnik_id"));
        datumNarudzbeIdKolona.setCellValueFactory(new PropertyValueFactory<>("datum_narudzbe"));
        for(Narudzba nx:n.getAll()){
            System.out.println(nx.toString());
        }

        narudzbeTable.getItems().clear();
        narudzbeTable.setItems(n.getAll());
        narudzbeTable.refresh();
    }

    public void onBrisiNarzdzbuButtonPressed(ActionEvent actionEvent) {
        Narudzba n;

    }
}
