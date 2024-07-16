package org.system.SystemeGestionLabs.BaseDeDonnees;

import com.google.gson.JsonObject;

public interface IDAO<T> {

    JsonObject chercher(String nom);
    JsonObject transfererResultat();
    boolean verifiePrescenceDonnees();
    boolean verifiePrescenceDonneeSpecifique(String nom);
    void setManager(DBManager dbManager);
    void inserer(T enregistrement);
}
