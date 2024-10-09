// Package fr.afpa.tools : Définit le package dans lequel se trouve l'interface Serializer.
package fr.afpa.tools;

// import java.util.ArrayList : Importe la classe ArrayList de la bibliothèque Java. ArrayList est une implémentation de la liste dynamique qui sera utilisée pour stocker une collection d'objets génériques T.
import java.util.ArrayList;

// Public interface Serializer<T> : Déclare une interface générique nommée Serializer. Le paramètre générique <T> permet de définir des méthodes qui fonctionneront avec n'importe quel type d'objet.
public interface Serializer<T> {

    // public abstract void saveList(String filesPath, ArrayList<T> objectsToSave) :
// public : La méthode est accessible de partout.
// abstract : La méthode doit être implémentée par toute classe qui implémente cette interface.
// void : La méthode ne retourne rien.
// saveList : Nom de la méthode.
// String filesPath : Paramètre de type String qui spécifie le chemin du fichier où la liste des objets sera sauvegardée.
// ArrayList<T> objectsToSave : Paramètre de type ArrayList<T> qui contient les objets à sauvegarder. T est le type générique spécifié lors de l'implémentation de l'interface.
    public abstract void saveList(String filesPath, ArrayList<T> objectsToSave);

// public abstract void save(String filePath, T objet) :
// public : La méthode est accessible de partout.
// abstract : La méthode doit être implémentée par toute classe qui implémente cette interface.
// void : La méthode ne retourne rien.
// save : Nom de la méthode.
//String filePath : Paramètre de type String qui spécifie le chemin du fichier où l'objet sera sauvegardé.
// T objet : Paramètre de type T représentant l'objet à sauvegarder. T est le type générique spécifié lors de l'implémentation de l'interface.
    public abstract void save(String filePath, T objet);
}