package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.ArtikalDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.StavkaNarudzbeDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import com.mysql.cj.log.Log;
import javafx.beans.Observable;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class KreiranjeNarudzbiController implements Initializable {
    public TableView<Artikal> artikliTable;
    public TableColumn artikalIdKolona;
    public TableColumn artikalNazivKolona;
    public TableColumn artikalCijenaKolona;
    public TableColumn artikalKolicinaKolona;

    public TableView<Artikal> odabraniArtikliTable;
    public TableColumn odabraniArtikalIdKolona;
    public TableColumn odabranaKolicinaKolona;

    public MenuButton menuButton;
    public MenuItem upravljanjeKupcimaMenuItem;
    public MenuItem upravljanjeArtiklimaMenuItem;
    public TextField idArtiklaText;
    public TextField kolicinaText;
    public Button dodajArtikalButton;
    public Button kreirajNarudzbuButton;
    NarudzbaDaoImpl n = new NarudzbaDaoImpl();
    StavkaNarudzbeDaoImpl sn=new StavkaNarudzbeDaoImpl();
    ArtikalDaoImpl a=new ArtikalDaoImpl();
    private ArtikalModel aModel=new ArtikalModel();
    public KreiranjeNarudzbiController() {
    }




    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artikalIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        artikalNazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv_artikla"));
        artikalCijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        artikalKolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));

        odabraniArtikalIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        odabranaKolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        bindAzuriranje();
        try {
            for (Artikal art : a.getAll()) {
                System.out.println(art.toString());
            }

            artikliTable.getItems().clear();
            artikliTable.setItems(a.getAll());
            artikliTable.refresh();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    ArrayList<Artikal> arrArt=new ArrayList<Artikal>() ;
    Narudzba nar=new Narudzba();
    StavkaNarudzbe stavka=new StavkaNarudzbe();
    public void onDodajArtikalButtonPressed(ActionEvent actionEvent) throws myException {
        System.out.println("ID:"+aModel.id.getValue()+"\n");
        System.out.println("KOL:"+aModel.kolicina.getValue());
        Artikal art =a.getById(aModel.id.getValue());
        int id = LoginController.ID;
        nar.setDatum_narudzbe(java.time.LocalDate.now());
        nar.setKorisnik_id(id);
try{
if(art.getKolicina()<aModel.kolicina.getValue()) throw new myException("Na stanju nema toliko komada artikla! ");
}catch (myException m){
    new Alert(Alert.AlertType.NONE, m.getMessage(), ButtonType.OK).show();
}
        stavka.setArtikal_id(aModel.id.getValue());
        stavka.setKolicina(aModel.kolicina.getValue());
        arrArt.add(art);

       odabraniArtikliTable.getItems().clear();
        odabraniArtikliTable.setItems(FXCollections.observableList(arrArt));
        odabraniArtikliTable.refresh();


    }
    public void onKreirajNarudzbuButtonPressed(ActionEvent actionEvent) throws myException {
        n.add(nar,stavka);
        odabraniArtikliTable.getItems().clear();
        odabraniArtikliTable.refresh();
        arrArt.clear();
a.skiniSaStanja(aModel.id.getValue(),aModel.kolicina.getValue());

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
    private void bindAzuriranje() {
        idArtiklaText.textProperty().bindBidirectional(aModel.id, new NumberStringConverter());
        kolicinaText.textProperty().bindBidirectional(aModel.kolicina, new NumberStringConverter());

    }

}

