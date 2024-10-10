package fr.afpa.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import fr.afpa.App;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import fr.afpa.App;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;
import java.io.Writer;
import java.time.LocalDate;

public class Contact implements Serializable, Jsonable {

    private static Logger logger = LogManager.getLogger(App.class);

    // SimpleStringProperty est utilisé pour mettre automiquement l'interface
    // graphique à jour(TableView) à chaque modification de la String
    private int id;   
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty genre;
    private ObjectProperty <LocalDate> dateDeNaissance;
    private SimpleStringProperty pseudo;
    private SimpleStringProperty adresse;
    private SimpleStringProperty telPerso;
    private SimpleStringProperty telPro;
    private SimpleStringProperty mail;
    private SimpleStringProperty codePostale;
    private SimpleStringProperty lienGit;

    public Contact(int id, String nom, String prenom,
            String genre, LocalDate dateDeNaissance2,
            String pseudo, String adresse,
            String telPerso, String telPro,
            String mail, String codePostale,
            String lienGit) {

        this.id = id;        
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.genre = new SimpleStringProperty(genre);
        this.dateDeNaissance = new SimpleObjectProperty(dateDeNaissance2);
        this.pseudo = new SimpleStringProperty(pseudo);
        this.adresse = new SimpleStringProperty(adresse);
        this.telPerso = new SimpleStringProperty(telPerso);
        this.telPro = new SimpleStringProperty(telPro);
        this.mail = new SimpleStringProperty(mail);
        this.codePostale = new SimpleStringProperty(codePostale);
        this.lienGit = new SimpleStringProperty(lienGit);
    }

    @Override
    public String toString() {
        return "Contact [nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", dateDeNaissance="
                + dateDeNaissance + ", pseudo=" + pseudo + ", adresse=" + adresse + ", telPerso=" + telPerso
                + ", telPro=" + telPro + ", mail=" + mail + ", codePostale=" + codePostale + ", lienGit=" + lienGit
                + "]";
    }

    

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom.getValue();// pour obtenir un String; car l'attribut à été déclaré en SimpleString
                              // Property.
    }

    public SimpleStringProperty getNomProperty() {
        return nom;// pour obtenir SimpleString Property.
    }

    public String getPrenom() {
        return prenom.getValue();
    }

    public SimpleStringProperty getPrenomProperty() {
        return prenom;
    }

    public String getGenre() {
        return genre.getValue();
    }

    public SimpleStringProperty getGenreProperty() {
        return genre;
    }


    public ObjectProperty getDateDeNaissanceProperty() {
        return dateDeNaissance;
    }
    public LocalDate getDateDeNaissance() {
        return dateDeNaissance.getValue();
    }

    public String getPseudo() {
        return pseudo.getValue();
    }

    public SimpleStringProperty getPseudoProperty() {
        return pseudo;
    }

    public String getAdresse() {
        return adresse.getValue();
    }

    public SimpleStringProperty getAdresseProperty() {
        return adresse;
    }

    public String getTelPerso() {
        return telPerso.getValue();
    }

    public SimpleStringProperty getTelPersoProperty() {
        return telPerso;
    }

    public String getTelPro() {
        return telPro.getValue();
    }

    public SimpleStringProperty getTelProProperty() {
        return telPro;
    }

    public String getMail() {
        return mail.getValue();
    }

    public SimpleStringProperty getMailProperty() {
        return mail;
    }

    public String getCodePostale() {
        return codePostale.getValue();
    }

    public SimpleStringProperty getCodePostaleProperty() {
        return codePostale;
    }

    public String getLienGit() {
        return lienGit.getValue();
    }

    public SimpleStringProperty getLienGitProperty() {
        return lienGit;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom.setValue(nom); // changer la valeur du string property
    }

    public void setPrenom(SimpleStringProperty prenom) {
        this.prenom = prenom;
    }

    public void setGenre(SimpleStringProperty genre) {
        this.genre = genre;
    }

    public void setDateDeNaissance(SimpleObjectProperty dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setPseudo(SimpleStringProperty pseudo) {
        this.pseudo = pseudo;
    }

    public void setAdresse(SimpleStringProperty adresse) {
        this.adresse = adresse;
    }

    public void setTelPerso(SimpleStringProperty telPerso) {
        this.telPerso = telPerso;
    }

    public void setTelPro(SimpleStringProperty telPro) {
        this.telPro = telPro;
    }

    public void setMail(SimpleStringProperty mail) {
        this.mail = mail;
    }

    public void setCodePostale(SimpleStringProperty codePostale) {
        this.codePostale = codePostale;
    }

    public void setLienGit(SimpleStringProperty lienGit) {
        this.lienGit = lienGit;
    }

    public static void serialize(Contact contact, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
    }

    @Override
    public void toJson(Writer writable) throws IOException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("nom", this.getNom());
        jsonObject.put("prenom", this.getPrenom());
        jsonObject.put("genre", this.getGenre());

        // traitement de la date de naissance
        // on est obligé de la transformer en chaîne de caractère car json-simple
        // ne sait pas sérialiser les objets de la classe "LocalDate"
        LocalDate dateNaissance = this.getDateDeNaissance();
        jsonObject.put("date_de_naissance", dateNaissance.toString());
        
        jsonObject.put("pseudo", this.getPseudo());
        jsonObject.put("adresse", this.getAdresse());
        jsonObject.put("telPerso", this.getTelPerso());
        jsonObject.put("mail", this.getMail());
        jsonObject.put("code_Postale", this.getCodePostale());
        jsonObject.put("lienGit", this.getLienGit());

        jsonObject.toJson(writable);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {

        oos.writeObject(nom.getValue());
        oos.writeObject(prenom.getValue());
        oos.writeObject(genre.getValue());
        oos.writeObject(dateDeNaissance.getValue());
        oos.writeObject(pseudo.getValue());
        oos.writeObject(adresse.getValue());
        oos.writeObject(telPerso.getValue());
        oos.writeObject(telPro.getValue());
        oos.writeObject(mail.getValue());
        oos.writeObject(codePostale.getValue());
        oos.writeObject(lienGit.getValue());

    }

    private void readObject(ObjectInputStream in) throws IOException {

        try {

            nom = new SimpleStringProperty((String) in.readObject());
            prenom = new SimpleStringProperty((String) in.readObject());
            genre = new SimpleStringProperty((String) in.readObject());
            dateDeNaissance = new SimpleObjectProperty((LocalDate) in.readObject());
            pseudo = new SimpleStringProperty((String) in.readObject());
            adresse = new SimpleStringProperty((String) in.readObject());
            telPerso = new SimpleStringProperty((String) in.readObject());
            telPro = new SimpleStringProperty((String) in.readObject());
            mail = new SimpleStringProperty((String) in.readObject());
            codePostale = new SimpleStringProperty((String) in.readObject());
            lienGit = new SimpleStringProperty((String) in.readObject());
            
        } catch (ClassNotFoundException e) {
            logger.debug(e.getMessage());
        } 
    }

    public String toVCard() {
        StringBuilder vcard = new StringBuilder();
        vcard.append("BEGIN:VCARD\n");
        vcard.append("VERSION:4.0\n");
        vcard.append("N:").append(nom.getValue()).append(";").append(prenom.getValue()).append("\n");
        vcard.append("FN:").append(prenom.getValue()).append(" ").append(nom.getValue()).append("\n");
        vcard.append("EMAIL:").append(mail.getValue()).append("\n");
        vcard.append("TEL;TYPE=CELL:").append(telPerso.getValue()).append("\n");
        vcard.append("TEL;TYPE=WORK:").append(telPro.getValue()).append("\n");
        vcard.append("ADR:").append(adresse.getValue()).append(";").append(codePostale.getValue()).append("\n");
        vcard.append("BDAY:").append(dateDeNaissance.getValue()).append("\n");
        vcard.append("URL:").append(lienGit.getValue()).append("\n");
        vcard.append("END:VCARD");
        return vcard.toString();
    }
}
