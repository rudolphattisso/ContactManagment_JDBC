
import java.sql.Connection;
import com.developpez.jdbc.ConnectionPostgreSQL;

public class Dao {
    

public abstract class DAO<T> {

    public Connection connect = ConnectionPostgreSQL.getInstance();
    
    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract  T find(long id);
    
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract T create(T obj);
    
    /**
     * Permet de mettre à jour les données d'une entrée dans la base 
     * @param obj
     */
    public abstract T update(T obj);
    
    /**
     * Permet la suppression d'une entrée de la base
     * @param obj
     */
    public abstract void delete(T obj);
}

}

}
