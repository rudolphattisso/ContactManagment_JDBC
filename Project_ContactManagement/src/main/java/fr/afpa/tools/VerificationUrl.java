package fr.afpa.tools;
import java.util.regex.*;

public class VerificationUrl {



    public static boolean isValidURL(String url) {

        

        //utilisation du site OWASP qui donne de bonne pratiques de sécurisation de méthodes.
        String regex = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:|:blank:]])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    // public static void main(String[] args) {
    //     String url = "https://example.com";
    //     System.out.println("Is URL valid? " + isValidURL(url));
    // }



}
