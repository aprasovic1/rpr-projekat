package ba.unsa.etf.rpr.rprprojekat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Main App Controller
 */
public class AppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}