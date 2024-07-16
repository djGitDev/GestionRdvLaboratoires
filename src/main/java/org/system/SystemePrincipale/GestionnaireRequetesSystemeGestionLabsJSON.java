package org.system.SystemePrincipale;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.Controleurs.ControllerPriseRDV;
import org.system.SystemeGestionLabs.Controleurs.ControllerResultatExamen;

import java.util.ArrayList;

public class GestionnaireRequetesSystemeGestionLabsJSON implements IGestionnaireRequetesSystemeGestionLabs<JsonObject> {

private Mediateur ecouteur;

    public JsonObject trouverRDV(DemandeRDV demande) {
        JsonObject demandeJSON = convertirDemande(demande);
        return  ControllerPriseRDV.trouverRDV(demandeJSON);
    }

    public void informerEcouteur(JsonObject jsonObjectRDV){
//        ecouteur = new Dispatcher();
//        ecouteur.ecouterRDV(jsonObjectRDV);
    }


    public  JsonObject convertirDemande(DemandeRDV demande){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(demande);
        return jsonElement.getAsJsonObject();
    }

    @Override
    public ArrayList<JsonObject> demandeTransfertResultats() {
        return ControllerResultatExamen.transferResultatExamen();
    }


}
