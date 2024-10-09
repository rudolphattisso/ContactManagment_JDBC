package fr.afpa.models;
import java.util.List;

import fr.afpa.Contact_2;




    public interface CtDao {
    

    
    List<Contact_2> getAllContacts();
    
    void save(Contact_2 contact);
    
    // void update(Contact contact);
    
    // void delete(int id);
}

