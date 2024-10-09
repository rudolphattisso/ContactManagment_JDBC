package fr.afpa;

import fr.afpa.models.ConnexionSql;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConnexionSql con = new ConnexionSql();
        con.getAllContacts();
        

    }
}
