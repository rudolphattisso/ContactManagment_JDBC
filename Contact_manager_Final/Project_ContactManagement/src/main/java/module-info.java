module fr.afpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires javafx.base;
    requires json.simple;
    requires org.jsoup;
    requires java.sql;

    opens fr.afpa to javafx.fxml;
    opens fr.afpa.controllers to javafx.fxml;
    exports fr.afpa;
    exports fr.afpa.controllers;
}
