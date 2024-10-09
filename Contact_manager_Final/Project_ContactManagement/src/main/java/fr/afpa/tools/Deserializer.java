package fr.afpa.tools;

import java.util.ArrayList;

import fr.afpa.models.Contact;

public interface Deserializer<T> {



    
    public abstract T load(String filePath);
    public abstract ArrayList<T> loadList(String filePath);
}