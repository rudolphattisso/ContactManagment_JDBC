package fr.afpa.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import fr.afpa.models.Contact;

// Implementation de l'interface
// Public class ContactvCardSerializer : Déclare la classe ContactvCardSerializer qui implémente l'interface Serializer<Contact>.
// Serialize(Contact contact) : Méthode vide, probablement destinée à être implémentée pour la sérialisation d'un seul objet Contact.
public class ContactvCardSerializer implements Serializer<Contact> {

    // Paramètres : filesPath (chemin du fichier) et objectsToSave (liste d'objets
    // Contact à sauvegarder).
    // Implicitly target all declaration contexts by omitting a @Target annotation
    // @Retention(RetentionPolicy.SOURCE)
    // public @interface SuppressWarnings {
    // Cible implicitement tous les contextes de déclaration en omettant une
    // annotation @Target
    // @Retention(RetentionPolicy.SOURCE)
    // public @interface SuppressWarnings {
    /**
     * L'ensemble des avertissements qui doivent être supprimés par le compilateur
     * dans
     * l'élément annoté. Les noms en double sont autorisés. Les deuxième et
     * suivants occurrences d'un nom sont ignorées. La présence de
     * noms d'avertissements non reconnus n'est <i>pas</i> une erreur : les
     * compilateurs doivent
     * ignorer tous les noms d'avertissements qu'ils ne reconnaissent pas. Ils sont,
     * cependant,
     * libres d'émettre un avertissement si une annotation contient un nom
     * d'avertissement non reconnu.
     * 
     * @return l'ensemble des avertissements à supprimer
     */
    // FileOutputStream et ObjectOutputStream : Similaires à la méthode save.
    // out.writeObject(objectsToSave) : Sérialise la liste d'objets et l'écrit dans
    // le fichier.
    // Gestion des exceptions : Similaire à la méthode save.
    @Override
    public void saveList(String filesPath, @SuppressWarnings("exports") ArrayList<Contact> objectsToSave) {
        try (FileOutputStream fileOut = new FileOutputStream(filesPath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(objectsToSave);
            System.out.println(" La liste de contact se sauvegarde sur " + filesPath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Save : Sauvegarde un objet Contact sous forme de vCard dans un fichier.
    // FilesPath : Chemin du fichier où le contact doit être sauvegardé.
    // Contact : Objet Contact à sauvegarder.
    // vCardContent : Construit la chaîne de caractères représentant le contenu de
    // la vCard en suivant le format vCard 4.0.
    // Path path = Paths.get(filesPath) : Crée un objet Path pour le chemin de
    // fichier spécifié.
    // Utilise Files.writeString pour écrire le contenu de la vCard dans le fichier
    // en utilisant l'encodage UTF-8.
    // Gestion des exceptions : Capture et imprime un message si le chemin est
    // invalide.
    @Override
    public void save(String filesPath, Contact contact) {
        /// 1 construire la chaîne de caractères correspondant au contenu vCard
        String vCardContent = "BEGIN:VCARD\nVERSION:4.0" + "\n";

        // Construction du Nom
        vCardContent = vCardContent + "N:" + contact.getNom() + ";" + contact.getPrenom() + ";;" + contact.getGenre()
                + ";" + "\n";

        // Construction du "Full name"
        vCardContent = vCardContent + "FN:" + contact.getPrenom() + " " + contact.getNom() + "\n";

        // Construction du "Birtday"
        vCardContent = vCardContent + "BIRTHDAY:" + contact.getDateDeNaissance() + "\n";
        
        // Construction du "adress"
        vCardContent = vCardContent + "ADR;type=HOME;type=pref:" + contact.getAdresse() + " " + contact.getCodePostale() + "\n";
        
        // Construction du "Mail"
        vCardContent = vCardContent + "item1.EMAIL.URL;type=INTERNET;type=pref:" + contact.getMail() + "\n";
       
        // Construction du "TelPerso"
        vCardContent = vCardContent + "TEL;TYPE=persnum, voice;TelPerso=uri:" + contact.getTelPerso() + " " + "\n";
        
        // Construction du "Tel Pro"
        vCardContent = vCardContent + "TEL;TYPE=persnum, voice;TelPro=uri:" + contact.getTelPro() + "\n";
        
        // Construction "link git"
        vCardContent = vCardContent + "item4.URL;type=pref:" + contact.getLienGit() + "\n";

        // Construction "END VCARD"
        vCardContent = vCardContent + "END:VCARD";
        
        // 2 Ecriture de la vCard dans un fichier
        Path path = Paths.get(filesPath);

        // Essayez le bloc pour vérifier les exceptions
        try {
            // Appel de la méthode Files.writeString()
            // avec chemin, contenu et jeux de caractères standard
            Files.writeString(path, vCardContent, StandardCharsets.UTF_8);
        }

        // Bloc Catch pour gérer l'exception
        catch (IOException ex) {
            // Une exception d'impression de message s'est produite comme
            // non valide. Le chemin local du répertoire est transmis
            System.out.print("Invalid Path");
        }

    }

}

// -----------------------------------------------------------------------------------

// import ezvcard.VCard;
// import ezvcard.VCardVersion;
// import ezvcard.property.StructuredName;
// import ezvcard.property.Telephone;
// import ezvcard.property.Email;
// import ezvcard.io.text.VCardWriter;

// import java.io.FileWriter;
// import java.io.IOException;

// public class ContactvCardSerializer{
// public static void main(String[] args) {
// // Créer une nouvelle vCard
// VCard vcard = new VCard();
// vcard.setFormattedName("John Doe");

// // Ajouter un nom structuré
// StructuredName n = new StructuredName();
// n.setFamily("Lasri");
// n.setGiven("Youssef");
// vcard.setStructuredName(n);

// // Ajouter un numéro de téléphone
// Telephone tel = new Telephone("+33 6 51 71 29 19");
// vcard.addTelephoneNumber(tel);

// // Ajouter une adresse e-mail
// Email email = new Email("Lamazir@gmail.com");
// vcard.addEmail(email);

// // Spécifier la version de vCard (par défaut, c'est 4.0)
// vcard.setVersion(VCardVersion.V4_0);

// // Sauvegarder la vCard dans un fichier
// try (FileWriter writer = new FileWriter("contact.vcf");
// VCardWriter vCardWriter = new VCardWriter(writer)) {
// vCardWriter.write(vcard);
// } catch (IOException e) {
// e.printStackTrace();
// }

// System.out.println("vCard créée avec succès!");
// }
// }