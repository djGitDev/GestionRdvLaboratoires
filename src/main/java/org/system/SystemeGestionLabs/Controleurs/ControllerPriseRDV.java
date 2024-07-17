package org.system.SystemeGestionLabs.Controleurs;

import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.BaseDeDonnees.DBManager;

public class ControllerPriseRDV {

    public static JsonObject trouverRDV(JsonObject demandeJSON) {
        DBManager manager = DBManager.getInstance();
        JsonObject jsonObjectDisponibilite = new JsonObject();
        JsonObject jsonObjectRDV = new JsonObject();
        if (presenceDisponibilite(demandeJSON)) {
            JsonObject descriptionObject = demandeJSON.getAsJsonObject("descriptionExamen");
            String nom = descriptionObject.get("nomExamen").getAsString();
            jsonObjectDisponibilite = manager.transfererDisponibilite(nom);
            String noDemande = demandeJSON.get("noDemande").getAsString();
            String codePatient = demandeJSON.get("codePatient").getAsString();
            String date = jsonObjectDisponibilite.get("date").getAsString();

            String nomLab = jsonObjectDisponibilite.get("nomLabo").getAsString();
            String heure =jsonObjectDisponibilite.get("heure").getAsString();
            // Ajouter les valeurs à l'objet JsonObject
            jsonObjectRDV.addProperty("noDemande", noDemande);
            jsonObjectRDV.addProperty("codePatient", codePatient);
            jsonObjectRDV.addProperty("nomLabo", nomLab);
            jsonObjectRDV.addProperty("date", date);
            jsonObjectRDV.addProperty("heure", heure);
            jsonObjectRDV.addProperty("disponibilite", "RDV disponible");

        } else {
            jsonObjectRDV.addProperty("disponibilite", "aucun RDV n'est disponible");
        }
        return jsonObjectRDV;
    }

    public static boolean presenceDisponibilite(JsonObject demandeJSON) {
        DBManager manager = DBManager.getInstance();
        JsonObject description = demandeJSON.getAsJsonObject("descriptionExamen");
        boolean estDisponible = manager.verifierDisponibilite(description.get("nomExamen").getAsString());
        return estDisponible;
    }

}
