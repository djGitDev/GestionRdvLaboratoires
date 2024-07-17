package org.system.SystemePrincipale.Examen;

import org.system.SystemePrincipale.DescriptionExamen;

import java.util.ArrayList;

public class Examen_Unique extends Examen{
    protected DescriptionExamen description;
    private String type;

    public Examen_Unique(int no,String nom,DescriptionExamen desc){
        super(no,nom);
        this.description = desc;

    }

    @Override
    public void afficherDetails() {
        description.afficherDetails();
    }

//    @Override
//    public void afficherDetails() {
//        System.out.println("Examen: " + nomExamen);
//        if (!parametres.isEmpty()) {
//            System.out.println("Paramètres: " + parametres);
//        }
//        if (partieDuCorps != null) {
//            System.out.println("Partie du corps: " + partieDuCorps);
//        }
//        if (type != null) {
//            System.out.println("Type: " + type);
//        }
//    }
    public DescriptionExamen getDescription() {
        return description;
    }



    @Override
    public String getNomExamen() {
        return description.getNom();
    }

    @Override
    public boolean isExamenElementaire() {
        return true;
    }

    @Override
    public ArrayList<Examen> getComposantExamenList() {
        return null;
    }
    public void addExamenParametre(String parametre) {
        description.ajouterParmetre(parametre);
    }

    @Override
    public String paramsToString() {
        return "";
    }
}
