module ba.unsa.etf.rpr.rprprojekat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens ba.unsa.etf.rpr.rprprojekat to javafx.fxml;
    exports ba.unsa.etf.rpr.rprprojekat;
    exports ba.unsa.etf.rpr.rprprojekat.controllers;
    opens ba.unsa.etf.rpr.rprprojekat.controllers to javafx.fxml;
}