package fr.afpa.models;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConnexionPostgreSql implements ContactDAO{

    public ArrayList<Contact> getAllContacts() {

        ArrayList <Contact> SqlallContacts= new ArrayList<Contact>();
        
    try { 
            // chaîne de connexion à la base de données 
            String url = "jdbc:postgresql://localhost:3155/contact"; 
            // création d'un objet de la classe "Connection" en utilisant DriverManager 
            Connection con = DriverManager.getConnection(url, "postgres", "B@nLgU4qz*9?D~3n83"); 
            System.out.println("connection etablie");   
            // création d'un "Statement" (objet qui permet d'exéctuer une requête SQL) 
            Statement stm = con.createStatement(); 
          
            // récupération de toutes les lignes de résultat (objet de la classe "ResultSet") 
            ResultSet result = stm.executeQuery("SELECT * FROM contact");
 
            // on passe en revue toutes les lignes 
            while (result.next()) { 
                
                // récupération des valeurs des colonnes 
                int id = result.getInt("id"); 
                String nom = result.getString("nom"); 
                String prenom = result.getString("prenom"); 
                String genre = result.getString("genre"); 
                LocalDate dateDeNaissance = LocalDate.parse(result.getString("date_de_naissance")); 
                String pseudo = result.getString("pseudo"); 
                String adresse = result.getString("adresse"); 
                String telPerso = result.getString("tel_perso"); 
                String telPro = result.getString("tel_pro"); 
                String mail = result.getString("mail"); 
                String codePostale = result.getString("code_postale"); 
                String gitLink = result.getString("git_link"); 
            //     affichage du résultat 
               System.out.format("[%d] %s %s\n", id, nom, prenom, genre, dateDeNaissance, pseudo, adresse, 
               telPerso, telPro, mail, codePostale, gitLink); 

                Contact contact = new Contact(id, nom, prenom, genre, dateDeNaissance, pseudo, adresse, telPerso, telPro, mail, codePostale, gitLink);
                SqlallContacts.add(contact);
            } 
           System.out.println(SqlallContacts.toString());
            // fermeture des ressources 
            stm.close(); 
            result.close(); 
            con.close(); 

            return SqlallContacts;
           
} catch (Exception e) { 
            System.err.println("Error"); 
            System.err.println(e.getMessage()); 
        return null;
        } 

    }


}
