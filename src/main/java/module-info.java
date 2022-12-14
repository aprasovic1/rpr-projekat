module ba.unsa.etf.rpr.rprprojekat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens ba.unsa.etf.rpr.rprprojekat to javafx.fxml;
    exports ba.unsa.etf.rpr.rprprojekat;
}