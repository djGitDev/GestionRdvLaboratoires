package org.system.SystemeGestionLabs.BaseDeDonnees;

import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.*;

public class DBManager {

    private static DBManager instance;
    private IDBconnexion dbConnection;
    private IDAO disponibiliesManger;
    private IDAO resultatExamenManager;

    // Constructeur privé
    private DBManager() {
        dbConnection = new SQLConnexion();
        disponibiliesManger = new DisonibilitesDAO();
        resultatExamenManager = new ResultatsExamensDAO();
        dbConnection.connect();
        resultatExamenManager.setManager(this);
        disponibiliesManger.setManager(this);
    }

    // Méthode statique pour récupérer l'instance singleton
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public void connect() {
        dbConnection.connect();
    }

    public JsonObject transfereResultatExamen() {
        return resultatExamenManager.transfererResultat();
    }

    public boolean verifiePrescenceDonnees() {
        return resultatExamenManager.verifiePrescenceDonnees();
    }

    public boolean verifierDisponibilite(String nom_examen){
        return disponibiliesManger.verifiePrescenceDonneeSpecifique(nom_examen);
    }

    public JsonObject transfererDisponibilite(String nom_examen) {
        return disponibiliesManger.chercher(nom_examen);
    }

    public IDBconnexion getConnection() {
        return dbConnection;
    }

    public void ajouterResultatExamen(ResultatExamen nouveauResultat) {
        resultatExamenManager.inserer(nouveauResultat);
    }


}