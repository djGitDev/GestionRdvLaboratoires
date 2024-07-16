package org.system.SystemePrincipale.Event;

import com.google.gson.JsonObject;

public class RendezVous extends Event {

    private int numDemande;
    private String codePatient;
    private int codeDoctor;
    private String nomLaboratoire;
    private String date;
    private String heure;
    private boolean estDisponible;



    public RendezVous() {
        super(Type.RDV);
        this.estDisponible = false;
    }
    public RendezVous(int numDemande,String codePatient,String lab, String date, String heure){
        super(Type.RDV);
        this.numDemande = numDemande;
        this.codePatient = codePatient;
        this.nomLaboratoire = lab;
        this.date = date;
        this.heure = heure;
        this.estDisponible = true;
    }

    public String getHeure() {
        return heure;
    }

    public String getDate() {
        return date;
    }

    public String getCodePatient() {
        return codePatient;
    }


    public int getNumDemande() {
        return numDemande;
    }

    public void setEstDisponible(boolean b) {
        this.estDisponible = b;
    }

    public boolean estDisponible() {
        return (date != null);
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "numDemande=" + numDemande +
                ", codePatient=" + codePatient +
                ", codeDoctor=" + codeDoctor +
                ", nomLaboratoire='" + nomLaboratoire + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", estDisponible=" + estDisponible +
                '}';
    }
}
