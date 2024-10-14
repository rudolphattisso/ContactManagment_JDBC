package fr.afpa.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class VerificationMail {
    // Définir le modèle regex pour une adresse email.
    // Modèle regex pour une adresse email valides.
    // (?=.{1,64}@.{4,255}$) : La longueur totale de l'adresse email ne doit pas
    // dépasser 320 caractères. La partie locale (avant le @) peut avoir jusqu'à 64
    // caractères, et la partie domaine (après le @) doit avoir entre 4 et 255
    // caractères.
    // (?=.{6,320}$) : La longueur totale de l'adresse email doit être comprise
    // entre 6 egit statust 320 caractères.
    // [a-zA-Z0-9._%+-]{1,64} : La partie locale de l'adresse email permet les
    // caractères alphanumériques et certains caractères spéciaux.
    // @[a-zA-Z0-9.-]+ : La partie domaine permet les caractères alphanumériques et
    // les tirets.
    // \\.[a-zA-Z]{2,}$ : La partie TLD doit avoir au moins deux caractères
    // alphabétiques.
    private static final String EMAIL_REGEX = "^(?=.{1,64}@.{4,255}$)(?=.{6,320}$)[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Compilation du modèle regex.
    // Cela compile le modèle regex une seule fois pour une utilisation répétée, ce
    // qui est plus efficace.
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Méthode pour valider une adresse email
    // Cette méthode utilise un Matcher pour vérifier si l'adresse email donnée
    // correspond au modèle regex. Si l'adresse email est null, elle retourne false.
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Cette partie du code teste plusieurs adresses email pour voir
    // si elles sont valides.
    // public static void main(String[] args) {
    //     String[] testEmails = {
    //             "simple@example.com",
    //             "very.common@example.com",
    //             "disposable.style.email.with+symbol@example.com",
    //             "other.email-with-dash@example.com",
    //             "fully-qualified-domain@example.com",
    //             "user.name+tag+sorting@example.com",
    //             "x@example.com",
    //             "example-indeed@strange-example.com",
    //             "admin@mailserver1",
    //             "user@localserver",
    //             "user@[IPv6:2001:db8::1]",
    //             "Abc.example.com",
    //             "A@b@c@example.com",
    //             "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",
    //             "just\"not\"right@example.com",
    //             "this is\"not\\allowed@example.com",
    //             "this\\ still\\\"not\\\\allowed@example.com",
    //             "1234567890123456789012345678901234567890123456789012345678901234x@example.com",
    //             "john..doe@example.com",
    //             "john.doe@example..com"
    //     };

    //     for (String email : testEmails) {
    //         System.out.println("Is " + email + " a valid email address? " + isValidEmail(email));
    //     }
    // }
}
