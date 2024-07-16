package org.system.SystemePrincipale;

import com.google.gson.JsonObject;
import org.system.SystemePrincipale.Examen.Examen;
import org.system.SystemePrincipale.Examen.Examen_Compose;
import org.system.SystemePrincipale.Examen.Examen_Unique;
import org.system.SystemePrincipale.Event.Event;
import org.system.SystemePrincipale.Event.RendezVous;
import org.system.SystemePrincipale.Event.ResultatExamenLaboratoire;
import org.system.SystemePrincipale.Notifieur.INotifieur;
import org.system.SystemePrincipale.Notifieur.NotifieurCourriel;
import org.system.SystemePrincipale.Notifieur.NotifieurSms;
import org.system.SystemePrincipale.Vue.NotificationPanel;

import java.util.ArrayList;

public class Mediateur implements IMediateur<JsonObject> {

    private IGestionnaireRequetesSystemeGestionLabs<JsonObject> gestionnaireLabs = new GestionnaireRequetesSystemeGestionLabsJSON();
    private INotifieur notifieur;
    private NotificationPanel panel;


    public Mediateur(NotificationPanel notificationPanel) {
        panel = notificationPanel;
        notifieur = new NotifieurCourriel(panel);
    }

    public void setNotificationType(String notificationType) {

        if (notificationType.equals("sms")){
            notifieur = new NotifieurSms(panel);
        }else{
        notifieur = new NotifieurCourriel(panel);
        }
    }

    public JsonObject traiterDemande(DemandeRDV demande) {
        return notifierRDV(demande);
    }

    public ArrayList<JsonObject> demandeTransfertResultat() {
        ArrayList<JsonObject> resultatsJson = (ArrayList<JsonObject>) gestionnaireLabs.demandeTransfertResultats();
        for (JsonObject resultatJson : resultatsJson) {
            ecouterResultatExamen(resultatJson);
        }
        return resultatsJson;
    }

    private void ecouterResultatExamen(JsonObject resultatExamenJson) {
        Event resultatExamen = convertirResultatExamen(resultatExamenJson);
        notifieur.notify(resultatExamen);
    }

    private JsonObject notifierRDV(DemandeRDV demande) {

        JsonObject jsonRDV = gestionnaireLabs.trouverRDV(demande);
        if (jsonRDV != null && jsonRDV.has("disponibilite") && "RDV disponible".equals(jsonRDV.get("disponibilite").getAsString())) {
            Event rdv = convertirRDV(jsonRDV);
            if (((RendezVous) rdv).estDisponible()) {
                notifieur.notify(rdv);
            }
        } else {
            System.err.println("RDV non disponible: " + jsonRDV);
        }
        return jsonRDV;
    }

    private Event convertirResultatExamen(JsonObject resultatExamenJson) {
        String codePatient = resultatExamenJson.get("codePatient").getAsString();
        int numeroRDV = resultatExamenJson.get("numeroRDV").getAsInt();
        String dateExamen = resultatExamenJson.get("dateExamen").getAsString();
        String uri = resultatExamenJson.get("uriDocument").getAsString();
        return new ResultatExamenLaboratoire(codePatient, numeroRDV, dateExamen, uri);
    }

    private Event convertirRDV(JsonObject jsonRDV) {
        if (jsonRDV.has("disponibilite") && "RDV disponible".equals(jsonRDV.get("disponibilite").getAsString())) {
            int noDemande = jsonRDV.get("noDemande").getAsInt();
            String codePatient = jsonRDV.get("codePatient").getAsString();
            String nomLabo = jsonRDV.get("nomLabo").getAsString();
            String date = jsonRDV.get("date").getAsString();
            String heure = jsonRDV.get("heure").getAsString();
            return new RendezVous(noDemande, codePatient, nomLabo, date, heure);
        } else {
            return new RendezVous();
        }
    }

    public ArrayList<DemandeRDV> extraireDemandes(Prescription prescription) {
        ArrayList<DemandeRDV> demandes = new ArrayList<>();
        Examen examen = prescription.getExamen();
        if (examen instanceof Examen_Compose) {
            Examen_Compose examenCompose = (Examen_Compose) examen;
            for (Examen subExamen : examenCompose.getComposantExamenList()) {
                if (subExamen instanceof Examen_Unique) {
                    DemandeRDV demandeRDV = new DemandeRDV(prescription.getPatient().getCodePatient(), ((Examen_Unique) subExamen).getDescription(), "01-01-2000");
                    demandes.add(demandeRDV);
                } else if (subExamen instanceof Examen_Compose) {
                    Examen_Compose examenSousCompose = (Examen_Compose) subExamen;
                    for (Examen underSubExamen : examenSousCompose.getComposantExamenList()) {
                        DemandeRDV demandeRDV = new DemandeRDV(prescription.getPatient().getCodePatient(), ((Examen_Unique) underSubExamen).getDescription(), "01-01-2000");
                        demandes.add(demandeRDV);
                    }
                }
            }
        } else if (examen instanceof Examen_Unique) {
            DemandeRDV demandeRDV = new DemandeRDV(prescription.getPatient().getCodePatient(), ((Examen_Unique) examen).getDescription(), "01-01-2000");
            demandes.add(demandeRDV);
        }
        return demandes;
    }
}
