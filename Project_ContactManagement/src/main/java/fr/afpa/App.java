package fr.afpa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import fr.afpa.controllers.ContactManagementController;
import fr.afpa.models.ConnexionPostgreSql;
import fr.afpa.models.Contact;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("contactManagementDatePicker"), 1024, 768);
        stage.setScene(scene);
        stage.show();

        //  TITLE IN STAGE 
        stage.setTitle("Gestion de la liste des contacts");

    //     // ICON
        Image icon = new Image(getClass().getResourceAsStream("contacts.png"));
        stage.getIcons().add(icon); // Add icon in stage
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ConnexionPostgreSql con = new   ConnexionPostgreSql();
        con.getAllContacts() ;
        launch();
        
    }

}