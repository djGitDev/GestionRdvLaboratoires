package org.system.SystemePrincipale.Examen;

import java.util.ArrayList;

public abstract class Examen  {

    protected String nomExamen;
    protected int noExamen;

    public Examen(int no,String nom) {
        this.nomExamen = nom;
        this.noExamen = no;

    }

    public abstract void afficherDetails();


    @Override
    public String toString() {
        return "Examen{" +
                "nomExamen='" + nomExamen + '\'' +
                '}';
    }
    public abstract String getNomExamen();
    public abstract boolean isExamenElementaire() ;
    public abstract ArrayList<Examen> getComposantExamenList();
    public abstract String paramsToString() ;



}
