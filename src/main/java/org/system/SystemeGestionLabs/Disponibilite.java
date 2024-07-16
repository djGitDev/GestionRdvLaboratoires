package org.system.SystemeGestionLabs;

public class Disponibilite {


    private Laboratoire labo;
    private String nomExamen;
    private String date;
    private String horaire;

    public Disponibilite(Laboratoire labo, String examen, String date, String horaire) {
        this.labo = labo;
        this.nomExamen = examen;
        this.date = date;
        this.horaire = horaire;
    }

    public String getDate() {
        return date;
    }

    public String getHoraire() {
        return horaire;
    }

    public Laboratoire getLabo() {
        return labo;
    }

    public String getExamen() {
        return nomExamen;
    }
}
