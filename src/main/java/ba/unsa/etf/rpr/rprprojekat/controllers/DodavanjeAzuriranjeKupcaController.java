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
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


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
        try {
            refreshTable();
        } catch (myException e) {
            throw new RuntimeException(e);
        }

idZaAzuriranjeTextField.textProperty().addListener((obs, old, n) -> {
    List<Korisnik> l;
    try {
        l=k.getAll();
    } catch (myException e) {
        throw new RuntimeException(e);
    }
    List<Korisnik> fil=l.stream().filter(k->k.getId()==Integer.parseInt(n)).collect(Collectors.toList());
    kModel.fromKorisnik(fil.get(0));
});
    }


    public void onAzurirajPostojecegKupcaButtonPressed(ActionEvent actionEvent) throws myException {
        Korisnik kor= k.getById(kModel.id.getValue());
        kor.setIme(kModel.ime.getValue());
        kor.setPrezime(kModel.prezime.getValue());
        kor.setUser(kModel.user.getValue());
        kor.setPass(kModel.pass.getValue());
        k.update(kor);
        refreshTable();
        idZaAzuriranjeTextField.clear();
        clearDodavanje();
    }

    public void onDodajKupcaButton(ActionEvent actionEvent) throws myException {
        Korisnik novi =new Korisnik();
        novi.setJesteAdmin(false);
        novi.setIme(kModel.ime.getValue());
        novi.setPrezime(kModel.prezime.getValue());
        novi.setUser(kModel.user.getValue());
        novi.setPass(kModel.pass.getValue());
        k.add(novi);
        refreshTable();
        clearDodavanje();
    }

    public void onTraziPoNazivuButtonPressed(ActionEvent actionEvent) throws myException {

        ArrayList<Korisnik> arr=new ArrayList<Korisnik>();
        if(imeZaPretraguTextField.textProperty().length().getValue()==0&&prezimeZaPretraguTextField.textProperty().length().getValue()==0)
            refreshTable();
        else {
            arr.add(k.getByName(imeZaPretraguTextField.getText(), prezimeZaPretraguTextField.getText()));
            prikazOsobaTableView.getItems().clear();
            prikazOsobaTableView.setItems(FXCollections.observableList(arr));
            prikazOsobaTableView.refresh();
        }
    }

    public void onUpravljanjeNarudzbamaPressed(ActionEvent actionEvent) {
        LoginController.openDialog("Prikaz/brisanje narudzbi", "/fxml/prikaz_brisanje_narudzbi.fxml", new PrikazBrisanjeNarudzbiController());
        Stage stara = (Stage) menuButton.getScene().getWindow();
        stara.hide();
    }
    public void onUpravljanjeArtiklimaPressed(ActionEvent actionEvent) {
        LoginController.openDialog("Dodavanje/Azuriranje artikla","/fxml/dodavanje_azuriranje_artikla.fxml",new DodavanjeAzuriranjeArtiklaController());
        Stage stara = (Stage) menuButton.getScene().getWindow();
        stara.hide();
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
        List<Korisnik> l;
        prikazOsobaTableView.getItems().clear();
        try {
            l=k.getAll();
        } catch (myException e) {
            throw new RuntimeException(e);
        }
        List<Korisnik> fil=l.stream().filter(k->!k.isJesteAdmin()).collect(Collectors.toList());
        prikazOsobaTableView.setItems(FXCollections.observableList(fil));
        prikazOsobaTableView.refresh();
    }
    private void bindTextFields(){
       idZaAzuriranjeTextField .textProperty().bindBidirectional(kModel.id,new NumberStringConverter());
        imeZaDodavanjeTextField .textProperty().bindBidirectional(kModel.ime);
        prezimeZaDodavanjeTextField.textProperty().bindBidirectional(kModel.prezime);
        usernameZaDodavanjeTextField.textProperty().bindBidirectional(kModel.user);
        passwordZaDodavanjeTextField.textProperty().bindBidirectional(kModel.pass);

    }
    private void clearDodavanje(){

        imeZaDodavanjeTextField.clear();
        prezimeZaDodavanjeTextField.clear();
        usernameZaDodavanjeTextField.clear();
        passwordZaDodavanjeTextField.clear();
    }
}
