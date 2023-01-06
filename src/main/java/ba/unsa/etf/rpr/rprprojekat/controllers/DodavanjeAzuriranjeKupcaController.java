package ba.unsa.etf.rpr.rprprojekat.controllers;
import ba.unsa.etf.rpr.rprprojekat.dao.KorisnikDaoImpl;
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


public class DodavanjeAzuriranjeKupcaController implements Initializable{

    public MenuButton menuButton;
    public Button tražiKupcaButton;
    public TableView prikazOsobuaTableView;
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

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }


    public void onAzurirajPostojecegKupcaButtonPressed(ActionEvent actionEvent) {
    }

    public void onDodajKupcaButton(ActionEvent actionEvent) {
    }

    public void onTraziPoNazivuButtonPressed(ActionEvent actionEvent) {
    }
}
