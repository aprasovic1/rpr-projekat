package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.Application;
import ba.unsa.etf.rpr.rprprojekat.dao.KorisnikDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController implements Initializable {

    public PasswordField pwdField;
    public TextField usrField;
    public Button loginBtn;
    public Label wrongPassLabel;


    public LoginController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setDefaultButton(true);wrongPassLabel.setText("Unesite");
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    protected void onLoginButtonClick () {

        try
        {

            KorisnikDaoImpl k=null;
            Korisnik kor=null ;
            try{
                k=new KorisnikDaoImpl();
                kor =k.getByUsername(usrField.getText());
            }
            catch(myException e){
                new Alert(Alert.AlertType.WARNING, "Korisnik ne postoji!", ButtonType.OK).show();
            }
            while(true) {
                if (!kor.getPass().equals(pwdField.getText())) {
                    System.out.println("Pogresan password!");
                    new Alert(Alert.AlertType.WARNING, "Pogresan password!", ButtonType.OK).show();
                    wait();
                }
                else break;


            }

            //openDialog("Prikaz/brisanje narudzbi","/fxml/prikaz_brisanje_narudzbi.fxml",new PrikazBrisanjeNarudzbiController());
            //openDialog("Dodavanje/Azuriranje artikla","/fxml/dodavanje_azuriranje_artikla.fxml",new DodavanjeAzuriranjeArtiklaController());
            openDialog("Dodavanje/Azuriranje artikla","/fxml/dodavanje_azuriranje_kupca.fxml",new DodavanjeAzuriranjeKupcaController());
            Stage stara = (Stage) loginBtn.getScene().getWindow();stara.hide();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }




}
