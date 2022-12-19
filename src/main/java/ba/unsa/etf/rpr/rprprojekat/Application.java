package ba.unsa.etf.rpr.rprprojekat;

import ba.unsa.etf.rpr.rprprojekat.dao.*;
import ba.unsa.etf.rpr.rprprojekat.domain.Artikal;
import ba.unsa.etf.rpr.rprprojekat.domain.Korisnik;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.domain.StavkaNarudzbe;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import ba.unsa.etf.rpr.rprprojekat.dao.KorisnikDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("kreiranjeAzuriranjeKupca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 430);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }


    public static class Test {
        static void test() throws myException, IOException {
            KorisnikDaoImpl k = new KorisnikDaoImpl();
            Korisnik trazeniK = k.getById(1);
            System.out.println(trazeniK.toString());

            ArtikalDaoImpl a = new ArtikalDaoImpl();
            Artikal trazeniA = a.getById(1);
            System.out.println(trazeniA.toString());

            NarudzbaDaoImpl
                    n = new NarudzbaDaoImpl();
            Narudzba trazenaN = n.getById(1);
            System.out.println(trazenaN.toString());

            StavkaNarudzbeDaoImpl s = new StavkaNarudzbeDaoImpl();
            StavkaNarudzbe trazenaS = s.getById(1);
            System.out.println(trazenaS.toString());

        }
    }

    public static void main(String[] args) throws myException {
        try {
            Test.test();
        } catch (Exception e) {
            throw new myException(e.getMessage(), e);
        }
    }
}