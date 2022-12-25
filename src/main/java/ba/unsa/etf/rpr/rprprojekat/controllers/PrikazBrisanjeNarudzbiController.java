package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaNarudzbeDaoImpl;
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

import java.net.URL;
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

    public PrikazBrisanjeNarudzbiController() {
    }


    public void onBrisiNarzdzbuButtonPressed(ActionEvent actionEvent) throws myException {
        int idInput = Integer.parseInt(idNarudzbeText.getText());
        new StavkaNarudzbeDaoImpl().delete(idInput);
        n.delete(idInput);
        idNarudzbeText.clear();
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        narudzbeTable = new TableView<Narudzba>();
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
            System.out.println(
                    e.getMessage()

            );


        }

    }
}
