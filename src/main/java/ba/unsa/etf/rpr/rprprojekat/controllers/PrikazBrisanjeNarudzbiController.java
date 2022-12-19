package ba.unsa.etf.rpr.rprprojekat.controllers;

import ba.unsa.etf.rpr.rprprojekat.dao.NarudzbaDaoImpl;
import ba.unsa.etf.rpr.rprprojekat.domain.Narudzba;
import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class PrikazBrisanjeNarudzbiController {
    public TableView<Narudzba> narudzbeTable;
    public TableColumn<Integer,Narudzba> narudzbaIdKolona;
    public TableColumn<Narudzba,Integer > korisnikIdKolona;
    public TableColumn<Narudzba,Date > datumNarudzbeIdKolona;
    public MenuButton menuButton;
    public TextField idNarudzbeText;
    public Button brisiNardzbuButton;


    public PrikazBrisanjeNarudzbiController() throws myException {
        try{
            NarudzbaDaoImpl n=new NarudzbaDaoImpl();
        ObservableList<Narudzba> lista= FXCollections.emptyObservableList();
lista.add(new Narudzba());
            for(Narudzba x:n.getAll() );
//lista.add(x);

        }catch(Exception e){throw new RuntimeException();}
    }

@FXML
public void Initialize(){
}

    public void onBrisiNarzdzbuButtonPressed(ActionEvent actionEvent) {
        Narudzba n;

    }
}
