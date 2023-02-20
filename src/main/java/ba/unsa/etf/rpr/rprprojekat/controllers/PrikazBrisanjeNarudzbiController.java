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
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

/**
 * Controller class for Showing and deleting orders
 */
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
        idNarudzbeText.textProperty().bindBidirectional( nModel.id, new NumberStringConverter());
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

        new StavkaNarudzbeDaoImpl().deleteWithNarudzbaID(nModel.id.getValue());
        n.delete(nModel.id.getValue());
        idNarudzbeText.clear();
        narudzbeTable.getItems().clear();
        narudzbeTable.setItems(n.getAll());
        narudzbeTable.refresh();
    }
    public void onUpravljanjeArtiklimaPressed(ActionEvent actionEvent) {
        LoginController.openDialog("Dodavanje/Azuriranje artikla","/fxml/dodavanje_azuriranje_artikla.fxml",new DodavanjeAzuriranjeArtiklaController());
        Stage stara = (Stage) menuButton.getScene().getWindow();
        stara.hide();
    }
    public void onUpravljanjeKupcimaPressed(ActionEvent actionEvent) {
        LoginController.openDialog("Dodavanje/Azuriranje kupaca", "/fxml/dodavanje_azuriranje_kupca.fxml", new DodavanjeAzuriranjeKupcaController());
        Stage stara = (Stage) menuButton.getScene().getWindow();
        stara.hide();
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

