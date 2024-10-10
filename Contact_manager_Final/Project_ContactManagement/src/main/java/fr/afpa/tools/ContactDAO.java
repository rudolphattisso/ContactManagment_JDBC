package fr.afpa.tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.afpa.App;
import fr.afpa.models.Contact;



public class ContactDAO extends Dao<Contact> {

    private static Logger logger = LogManager.getLogger(App.class);

    @Override
    public List<Contact> getAll() {
        ArrayList<Contact> sqlAllContacts = new ArrayList<Contact>();
        try {
            //Connexion à la base de donnée;
            Connection con = ConnectionPostgreSQL.getInstance();

            Statement stm = con.createStatement();
            // récupération de toutes les lignes de résultat (objet de la classe
            // "ResultSet")
            ResultSet result = stm.executeQuery("SELECT * FROM contacts");
            // on passe en revue toutes les lignes
            while (result.next()) {
                // récupération des valeurs des colonnes pour chaque contact
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String genre = result.getString("genre");
                LocalDate dateDeNaissance = LocalDate.parse(result.getString("date_de_naissance"));
                String pseudo = result.getString("pseudo");
                String adresse = result.getString("addresse");
                String telPerso = result.getString("tel_perso");
                String telPro = result.getString("tel_pro");
                String mail = result.getString("mail");
                String codePostale = result.getString("code_postale");
                String gitLink = result.getString("git_link");

                Contact contact = new Contact(id, nom, prenom, genre, dateDeNaissance, pseudo, adresse, telPerso,
                        telPro, mail, codePostale, gitLink);

                sqlAllContacts.add(contact);
                System.out.println(sqlAllContacts);
                

                // affichage du résultat
                // System.out.format("[%d] %s %s\n", id, nom, prenom, genre, dateDeNaissance,
                // pseudo, adresse,
                // telPerso, telPro, mail, codePostale, gitLink);

            }
            // fermeture des ressources
            stm.close();
            result.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Error");
            System.err.println(e.getMessage());
        }
        // throw new UnsupportedOperationException("Unimplemented method 'getAll'");
        // }
        return sqlAllContacts;
    }
    

    @Override
    public Contact getById(long id) {
        try {
  
            Connection con = ConnectionPostgreSQL.getInstance();

            String sql = "SELECT * FROM contacts WHERE id = ?";
            // création d'un "Statement" pour effectuer la requête
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, id);

            ResultSet result = stm.executeQuery();
            while (result.next()) {
                // récupération des informations de l'enregistrement "Contact" ciblé
                // pas l'identifiant passé en paramètre de cette fonction
                int idContact = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String genre = result.getString("genre");
                LocalDate dateDeNaissance = LocalDate.parse(result.getString("date_de_naissance"));
                String pseudo = result.getString("pseudo");
                String adresse = result.getString("addresse");
                String telPerso = result.getString("tel_perso");
                String telPro = result.getString("tel_pro");
                String mail = result.getString("mail");
                String codePostale = result.getString("code_postale");
                String gitLink = result.getString("git_link");

                Contact contact = new Contact(idContact, nom, prenom, genre, dateDeNaissance, pseudo, adresse, telPerso,
                        telPro, mail, codePostale, gitLink);

                stm.close();
                con.close();
                System.out.println(contact);
                return contact;
            }

        } catch (Exception e) {
            System.err.println("Error");
            System.err.println(e.getMessage());
            // return null;
        }
        return null;

    }

    @Override
    public void add(Contact contact) {
        try {
            Connection con = ConnectionPostgreSQL.getInstance();
            
            String sql = "INSERT INTO contacts ( nom, prenom, genre, date_de_naissance, pseudo, addresse, tel_perso, tel_pro, mail, code_postale, git_link) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);

            // Définir les paramètres avec les setString(), setInt() etc.
            // stm.setInt(1, 1); // id
            stm.setString(1, contact.getNom()); // nom
            stm.setString(2, contact.getPrenom()); // prenom
            stm.setString(3, contact.getGenre()); // genre
            stm.setDate(4, Date.valueOf(contact.getDateDeNaissance()));
            stm.setString(5, contact.getPseudo()); // pseudo
            stm.setString(6, contact.getAdresse()); // addresse
            stm.setString(7, contact.getTelPerso()); // tel_perso
            stm.setString(8, contact.getTelPro()); // tel_pro
            stm.setString(9, contact.getMail()); // mail
            stm.setString(10, contact.getCodePostale()); // code_postale
            stm.setString(11, contact.getLienGit()); // git_link

            stm.execute();
            stm.close();
            con.close();

            System.out.println("le contact "+ contact.getNom()+ " " + contact.getPrenom() + "à été rajouté");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    
    @Override  
    public boolean deleteBySurnameAndFirstName(String surName, String firstName){
        try {
            // cas de la connexion avec singleton
        
            Connection con = ConnectionPostgreSQL.getInstance();
            
            String sql = "delete  from contacts  where nom like ? and prenom like ? ; ";
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setString(1, surName);
            stm.setString(2, firstName);
            stm.execute();
            stm.close();
            con.close();
            
            System.out.println(
                    "le contact dont les nom et prenoms sont: " + surName + " " + firstName + " a bien été supprimé");
                    return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteById(long id) {
        try {
            
            Connection con = ConnectionPostgreSQL.getInstance();
            String sql = "delete from contacts where id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setLong(1, id);
            
            stm.execute();
            stm.close();
            con.close();
            System.out.println("le contact dont l'id es " + id + " a bien été supprimé");
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(int id, String surName) {
        try {
            Connection con = ConnectionPostgreSQL.getInstance();
            String sql = "UPDATE contacts  SET nom = ? WHERE  id = ? ";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(2, id);
            stm.setString(1, surName);

            stm.execute();
            stm.close();
            con.close();

            System.out.println("le contact dont le nomest : " + surName + " a bien été modifié");
           
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
        
    }
    
}
