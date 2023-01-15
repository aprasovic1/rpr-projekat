package ba.unsa.etf.rpr.rprprojekat.controllers;
import ba.unsa.etf.rpr.rprprojekat.dao.KorisnikDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DodavanjeAzuriranjeKupcaController implements Initializable{

    public MenuButton menuButton;
    public Button tražiKupcaButton;
    public TableView prikazOsobaTableView;
    public TableColumn idKolona;
    public TableColumn imeKolona;
    public TableColumn prezimeKolona;
    public TableColumn userKolona;
    public TableColumn passKolona;
    public TextField imeZaDodavanjeTextField;
    public TextField prezimeZaDodavanjeTextField;
    public TextField usernameZaDodavanjeTextField;
    public TextField passwordZaDodavanjeTextField;
    public Button dodajKupcaButton;
    public TextField idZaAzuriranjeTextField;
    public Button azurirajPostojecegKupcaButton;
    public TextArea imeZaPretraguTextField;
    public TextArea prezimeZaPretraguTextField;

    KorisnikDaoImpl k = new KorisnikDaoImpl();
    private DodavanjeAzuriranjeKupcaController.KorisnikModel kModel=new DodavanjeAzuriranjeKupcaController.KorisnikModel();

    public DodavanjeAzuriranjeKupcaController() {
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        idKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
       imeKolona.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeKolona.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        userKolona.setCellValueFactory(new PropertyValueFactory<>("user"));
        passKolona.setCellValueFactory(new PropertyValueFactory<>("pass"));
        bindTextFields();
    }


    public void onAzurirajPostojecegKupcaButtonPressed(ActionEvent actionEvent) {
    }

    public void onDodajKupcaButton(ActionEvent actionEvent) {
    }

    public void onTraziPoNazivuButtonPressed(ActionEvent actionEvent) throws myException {

        ArrayList<Korisnik> arr=new ArrayList<Korisnik>();
        arr.add(k.getByName(imeZaPretraguTextField.getText(),prezimeZaPretraguTextField.getText()));
        prikazOsobaTableView.getItems().clear();
        prikazOsobaTableView.setItems(FXCollections.observableList(arr));
        prikazOsobaTableView.refresh();

    }

    public class KorisnikModel {
        public SimpleIntegerProperty id = new SimpleIntegerProperty();
        public SimpleStringProperty ime = new SimpleStringProperty(),
                prezime= new SimpleStringProperty(),
                user= new SimpleStringProperty(),
                pass= new SimpleStringProperty();

        public void fromKorisnik(Korisnik k) {
            this.id.set(k.getId());
            this.ime.set(k.getIme());
            this.prezime.set(k.getPrezime());
            this.user.set(k.getUser());
            this.pass.set(k.getPass());
        }

        public Korisnik toKorisnik() {
            Korisnik k = new Korisnik();
            k.setId(this.id.getValue());
            k.setIme(this.ime.getValue());
            k.setPrezime(this.prezime.getValue());
            k.setUser(this.user.getValue());
            k.setPass(this.pass.getValue());
            return k;
        }

    }
    private void refreshTable() throws myException {
        prikazOsobaTableView.getItems().clear();
        prikazOsobaTableView.setItems(k.getAll());
        prikazOsobaTableView.refresh();
    }
    private void bindTextFields(){
       idZaAzuriranjeTextField .textProperty().bindBidirectional(kModel.id,new NumberStringConverter());
        imeZaDodavanjeTextField .textProperty().bindBidirectional(kModel.ime);
        prezimeZaDodavanjeTextField.textProperty().bindBidirectional(kModel.prezime);
        usernameZaDodavanjeTextField.textProperty().bindBidirectional(kModel.user);
        passwordZaDodavanjeTextField.textProperty().bindBidirectional(kModel.pass);

    }
}
