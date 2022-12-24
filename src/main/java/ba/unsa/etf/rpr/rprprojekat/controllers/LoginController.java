package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.Application;
import ba.unsa.etf.rpr.rprprojekat.dao.KorisnikDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    public PasswordField pwdField;
    public TextField usrField;
    public Button loginBtn;


    public LoginController() {}
    public void initialize(){
        loginBtn.setDefaultButton(true);

    }



    @FXML
    protected void onLoginButtonClick () {
        try {
            KorisnikDaoImpl k=new KorisnikDaoImpl();
            try {
                Korisnik kor =k.getByUsername(usrField.getText());

                while(!kor.getPass().equals(pwdField.getText())){

                }



            }catch (Exception e){

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/prikaz_brisanje_narudzbi.fxml"));
            PrikazBrisanjeNarudzbiController prikaz = new PrikazBrisanjeNarudzbiController();
            Stage stara = (Stage) loginBtn.getScene().getWindow();
            loader.setController(prikaz);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Prikaz/brisanje narudzbi");
stara.hide();
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
