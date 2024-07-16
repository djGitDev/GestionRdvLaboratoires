package org.system.SystemeGestionLabs.Controleurs;

import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.BaseDeDonnees.DBManager;
import org.system.SystemeGestionLabs.ResultatExamen;

import java.util.ArrayList;


public class ControllerResultatExamen {


    public static ArrayList<JsonObject> transferResultatExamen() {
        ArrayList<JsonObject>   resultats = new ArrayList<>();
        while(aUnTravail()) {
            DBManager manager = DBManager.getInstance();
            resultats.add(manager.transfereResultatExamen());

        }
        return resultats;
    }


    public static boolean aUnTravail() {
        DBManager manager = DBManager.getInstance();
        boolean aDuTravail= manager.verifiePrescenceDonnees();
        return aDuTravail;
    }



    public void effectuerExamen(int numDemande, String codePatient, String nomExam) {

        ResultatExamen nouveauResultat =  ResultatExamen.effectuerExamen(numDemande
        , codePatient, nomExam);
        DBManager manager = DBManager.getInstance();
        manager.ajouterResultatExamen(nouveauResultat);


    }
}
