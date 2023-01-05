package ba.unsa.etf.rpr.rprprojekat.controllers;
import ba.unsa.etf.rpr.rprprojekat.dao.ArtikalDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DodavanjeAzuriranjeArtiklaController implements Initializable{

    public TableView<Artikal> prikazArtikalaTableView;
    public TableColumn<Integer, Artikal> artikalIdKolona;
    public TableColumn<String, Artikal> artikalNazivKolona;
    public TableColumn<Integer, Artikal> artikalCijenaKolona;
    public TableColumn<Integer, Artikal> artikalKolicinaKolona;
    public MenuButton menuButton;
    public TextField nazivZaPretraguTextField;
    public Button tražiPoNazivuButton;
    public TextField nazivZaDodavanjeTextField;
        public TextField cijenaZaDodavanjeTextField;
    public TextField kolicinaZaDodavanjeTextField;
    public Button dodajArtikalButton;
    public TextField idZaAzuriranjeTextField;
    public TextField kolicinaZaAzuriranjeTextField;
    public Button azurirajButton;

    ArtikalDaoImpl a = new ArtikalDaoImpl();
    private DodavanjeAzuriranjeArtiklaController.ArtikalModel aModel=new DodavanjeAzuriranjeArtiklaController.ArtikalModel();
    public DodavanjeAzuriranjeArtiklaController() {
    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artikalIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        artikalCijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        artikalNazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv_artikla"));
        artikalKolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));

        try {
            for (Artikal nx : a.getAll()) {
                System.out.println(nx.toString());
            }

            prikazArtikalaTableView.getItems().clear();
            prikazArtikalaTableView.setItems(a.getAll());
            prikazArtikalaTableView.refresh();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }



    public void onAzurirajButtonPressed(ActionEvent actionEvent) throws myException {
        idZaAzuriranjeTextField.textProperty().bindBidirectional((Property<String>) aModel.id.asString());
        kolicinaZaAzuriranjeTextField.textProperty().bindBidirectional( (Property<String>) aModel.kolicina.asString() );
        Artikal artikal= a.getById(aModel.id.getValue());
        artikal.setKolicina(artikal.getKolicina()+aModel.kolicina.getValue());
        a.update(artikal);
        idZaAzuriranjeTextField.clear();
        kolicinaZaAzuriranjeTextField.clear();
        prikazArtikalaTableView.refresh();
    }

    public void onDodajArtikalButtonPressed(ActionEvent actionEvent) throws myException {
        nazivZaDodavanjeTextField.textProperty().bindBidirectional( aModel.naziv);
        cijenaZaDodavanjeTextField.textProperty().bindBidirectional(aModel.cijena,new NumberStringConverter());
        kolicinaZaDodavanjeTextField.textProperty().bindBidirectional( aModel.kolicina,new NumberStringConverter());
        Artikal artikal= new Artikal();
        artikal.setKolicina(aModel.kolicina.getValue());
        artikal.setCijena(aModel.cijena.getValue());
        artikal.setNaziv_artikla(aModel.naziv.getValue());
        a.add(artikal);
        nazivZaDodavanjeTextField.clear();
   cijenaZaDodavanjeTextField.clear();
   kolicinaZaDodavanjeTextField.clear();
        prikazArtikalaTableView.refresh();
    }

    public void onTraziPoNazivuButtonPressed(ActionEvent actionEvent) {
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



}
