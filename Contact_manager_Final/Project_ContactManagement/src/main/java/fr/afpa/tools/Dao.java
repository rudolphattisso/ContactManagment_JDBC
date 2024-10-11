package fr.afpa.tools;

import java.sql.Connection;
import java.util.List;

import fr.afpa.models.Contact;

public abstract class Dao<T> {

    public Connection con = ConnectionPostgreSQL.getInstance();

    /**
      * Permet de récupérer tous les contacts
     * 
     * @param
     * @return
     */
    public abstract List<T> getAll();

    /**
     * Permet de récupérer un objet via son ID
     * 
     * @param id
     * @return
     */

    public abstract T getById(long id);

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * 
     * @param obj
     */
    public abstract void add(T object);

    /**
     * Permet la suppression d'une entrée de la base par id
     * 
     * @param obj
     */
    public abstract boolean deleteById(long id);
    

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     * 
     * @param obj
     */

     //dans l'objet Object il y aura l'id donc pas besoin de le mmettre en paramètres
    // public abstract boolean update(int id, String Surname);
    
    public abstract boolean update( T object);
    
}
