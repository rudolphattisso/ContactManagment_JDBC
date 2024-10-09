package fr.afpa.controllers;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import fr.afpa.App;
import fr.afpa.models.ConnexionPostgreSql;
import fr.afpa.models.Contact;
import fr.afpa.models.ContactDAO;
import fr.afpa.tools.ContactBinarySerializer;
import fr.afpa.tools.ContactJsonSerializer;
import fr.afpa.tools.VerificationMail;
import fr.afpa.tools.VerificationUrl;
import fr.afpa.tools.ContactvCardSerializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ContactManagementController {
    private ContactDAO contactDAO;
    private static Logger logger = LogManager.getLogger(App.class);
    // faire le lien entre les fichiers le controlleur et la vue ContactManagaement
    // tableView

    @FXML
    private TableView<Contact> tableViewContact;
    @FXML
    private TableColumn<Contact, String> colPrenom;

    @FXML
    private TableColumn<Contact, String> colNom;
    @FXML
    private TableColumn<Contact, String> colGenre;
    @FXML
    private TableColumn<Contact, String> colMail;
    @FXML
    private TableColumn<Contact, String> colTel;
    // boutons
    @FXML
    private Button boutonJson;
    @FXML
    private Button boutonVCard;
    @FXML
    private Button boutonSupprimer;
    @FXML
    private Button boutonModifier;
    @FXML
    private Button boutonCreer;
    // textFields
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField numPersoField;
    @FXML
    private TextField numProField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField lienGitField;

    /**
     * Méthode qui se déclenche sur un clic sur le bouton "Export JSon"
     * 
     * @param event Objet de la classe "ActionEvent" qui stocke les informations sur
     *              l'évènement en question
     */
    @FXML
    public void jsonExport(ActionEvent event) {

        // Intanciation d'un serializer
       
        ContactJsonSerializer serializer = new ContactJsonSerializer();

        // récupération du ou des contacts sélectionné
        ObservableList<Contact> selectedContacts = tableViewContact.getSelectionModel().getSelectedItems();
        if (selectedContacts.size() == 1) {

            Contact selectedContact = selectedContacts.getFirst();
            // appel à la méthode de sauvegarde (sérialisation)
            serializer.save("contact-" + selectedContact.getNom() + "-" + selectedContact.getPrenom() + ".json",
                    selectedContact);
        } else {
            serializer.saveList("contacts.json", new ArrayList<Contact>(contactsObservableList));
        }

    }

    /**
     * Méthode qui se déclenche sur un clic sur le bouton "Export JSon"
     * 
     * @param event Objet de la classe "ActionEvent" qui stocke les informations sur
     *              l'évènement en question
     */

    @FXML
    public void vCardExport(ActionEvent event) {
        ContactvCardSerializer vCardSerializer = new ContactvCardSerializer();

        Contact selectedContact = tableViewContact.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            System.out.println("Aucun contact sélectionné");
            return;
        } else {
            vCardSerializer.save(
                    selectedContact.getGenre() + selectedContact.getNom() + selectedContact.getPrenom() + ".vcf",
                    selectedContact);
        }

    }

    private ObservableList<Contact> contactsObservableList = FXCollections.observableArrayList(); // Observable liste pour
                                                                                            // stocker les contacts

    /**
     * méthode qui se lance dès le lancement du logiciel.
     */
    @FXML
    public void initialize() {

        // Chargement des différents possibles dans la comboBox
        genreComboBox.getItems().addAll("Homme", "Femme", "Non binaire");

        ContactBinarySerializer binaryDeserializer = new ContactBinarySerializer();
        
        // ArrayList<Contact> deserializedContacts = binaryDeserializer.loadList("contact.ser");

       // condition dans le cas ou la désirialisaton est impossible car le fichier de binaire n'existe pas au premier demarrage de l'application
       //la condition implique que rien ne doit être fait si le tableau est vide et effectuer l'action si dessous si c'estle contraire.

        // if(deserializedContacts!=null){
        //     contactsObservableList.addAll(deserializedContacts);
        // }
        

        tableViewContact.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //instanciation d'un objet de la classe ConnexionPostgreSql qui permettra de recuperer les 
         //données dans la base de données. Ces données données seront stockées dans une liste de contacts 

        ConnexionPostgreSql con = new ConnexionPostgreSql();
       
        ArrayList<Contact> contacts = con.getAllContacts();

       //  et au final, cette permettra de  rajouter les contacts dans l'observable liste pour le tableau 
        for (Contact contact : contacts) {
            contactsObservableList.add(contact);
        }
        // création du lien entre l'élement graphique TableView et le
        // tableau(ObservableList).
        tableViewContact.setItems(contactsObservableList);

        // La confiiguration d'affichage de chaque cellules
        colNom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
        colMail.setCellValueFactory(cellData -> cellData.getValue().getMailProperty());
        colTel.setCellValueFactory(cellData -> cellData.getValue().getTelPersoProperty());

        prenomField.setText("izhdfz");
        nomField.setText("izhdfz");
        adresseField.setText("izhdfz");
        pseudoField.setText("izhdfz");
        numPersoField.setText("izhdfz");
        numProField.setText("izhdfz");
        mailField.setText("r@50.com");
        datePicker.setValue(LocalDate.of(1995, 6, 5));
        lienGitField.setText("https://gemzo.com");

        // Contact contacts = new Contact(0, null, null, null, null, null, null, null, null, null, null, null);
        // ContactDAO.getAllContacts();

    }

    /**
     * Méthode permettant rajouter un contact à la liste de contacts;
     * elle verifie aussi le format de contenu du champ du mail et de l'url.
     * elle utilise aussi la serialisation binaire afin d'enreigistrer la liste
     * de contacts;
     * 
     * @param event : Objet de la classe "ActionEvent" qui stocke les informations
     *              sur
     *              l'évènement en question
     */
    @FXML
    public void creer(ActionEvent event) {
        // déclaration des variables dans lesquelles seront stockées le contenu de
        // champs
        // mail et url avant vérification.
        String mailValide = null;
        String urlValide = null;

        //pour créer les contrainte de synthaxe/format
        String genreSelection = genreComboBox.getSelectionModel().getSelectedItem();
        LocalDate dateSelection = datePicker.getValue();
        String nomRempli = nomField.getText();
        String prenomRempli = prenomField.getText();
        String numRempli = numPersoField.getText();
        String adresseRempli = adresseField.getText();




        // processus de verification des champs qui utiliseront les classes qui
        // contiennent*
        // les méthodes de vérificatoin
        Boolean checkMail = VerificationMail.isValidEmail(mailField.getText());
        Boolean checkUrl = VerificationUrl.isValidURL(lienGitField.getText());
        
        // cas ou les contenus des champs sont valides. TO DO : contraindre l'ajout du contact si le nom et le prenom ne sont pas renseignés
        if (checkMail == true && checkUrl == true && genreSelection != null && nomRempli!=null && prenomRempli != null && numRempli != null && adresseRempli != null) {
            mailValide = mailField.getText();
            urlValide = lienGitField.getText();
            genreSelection = genreComboBox.getSelectionModel().getSelectedItem();
            // dateSelection = datePicker.getValue();


            // création du contact;
          //  Contact contact = new Contact(nomField.getText(), prenomField.getText(), genreSelection,
            //         dateSelection, pseudoField.getText(), adresseField.getText(),
            //         numPersoField.getText(),
            //         numProField.getText(), mailValide, adresseField.getText(), urlValide);
            // contactsObservableList.add(contact);

            // serialisation binaire des contacts:
            ContactBinarySerializer serializer = new ContactBinarySerializer();
            serializer.saveList("contact.ser", new ArrayList<Contact>(contactsObservableList));
            // suppression du style CSS qui s'active en cas d'erreur appliqué au champs lors
            // d'une saisie précédente
            mailField.getStyleClass().remove("error-field");
            lienGitField.getStyleClass().remove("error-field");
            // retirer supprimer les infos des champs après ajout du contact
            nomField.setText("");
            prenomField.setText("");
            genreComboBox.getSelectionModel().clearSelection();
            dateSelection.now();
            pseudoField.setText("");
            adresseField.setText("");
            numPersoField.setText("");
            numProField.setText("");
            mailField.setText("");
            lienGitField.setText("");
            // cas du formats d'url et mail faux
            tableViewContact.getSelectionModel().clearSelection();
            // tableViewContact.getSelectionModel().selectLast();
            
        } else {
            if (checkMail == false) {
                mailField.getStyleClass().add("error-field");
            }
            if (checkUrl == false) {
                lienGitField.getStyleClass().add("error-field");
            }
            if (genreSelection != "Choix du genre") {
                genreComboBox.getStyleClass().add("error-field");
            }


        }

        logger.info("click créer");
    }

    /**
     * Méthode permettant modifier un contact selectionné dans la liste,
     * 
     * @param event : : Objet de la classe "ActionEvent" qui stocke les informations
     *              sur
     *              l'évènement en question
     */
    @FXML
    public void modifier(ActionEvent event) {
        // selection du contact dans la liste et stockage dans une variable
        Contact selectedContact = tableViewContact.getSelectionModel().getSelectedItem();
        // suppression du contact:
        tableViewContact.getItems().remove(selectedContact);
        // envoi des valeur du contacts dans les champs:
        nomField.setText(selectedContact.getNom());
        prenomField.setText(selectedContact.getPrenom());
        genreComboBox.getSelectionModel().select(selectedContact.getGenre());
        // dateDeNaissanceField.setText(selectedContact.getDateDeNaissance());
        datePicker.setValue(selectedContact.getDateDeNaissance());
        pseudoField.setText(selectedContact.getPseudo());
        adresseField.setText(selectedContact.getAdresse());
        numPersoField.setText(selectedContact.getTelPerso());
        numProField.setText(selectedContact.getTelPro());
        mailField.setText(selectedContact.getMail());
        lienGitField.setText(selectedContact.getLienGit());

    }

    /**
     * Méthode permettant de supprimer un contact dans la liste. elle utilise aussi
     * la serialisation binaire afin d'enreigistrer la liste de contacts;
     * 
     * @param event : : Objet de la classe "ActionEvent" qui stocke les informations
     *              sur
     *              l'évènement en question
     */
    @FXML
    public void supprimer(ActionEvent event) {

        Contact selectedItem = tableViewContact.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet élément ?");
            alert.setContentText("Cette action est irréversible.");

            if (alert.showAndWait().get() == ButtonType.OK) {
                tableViewContact.getItems().remove(selectedItem);

                // serialisation binaire des contacts:
                ContactBinarySerializer binarySerializer = new ContactBinarySerializer();
                binarySerializer.saveList("contact.ser", new ArrayList<Contact>(contactsObservableList));
            }
        }
    }


}
