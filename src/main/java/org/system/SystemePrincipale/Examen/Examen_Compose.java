package org.system.SystemePrincipale.Examen;

import java.util.ArrayList;

public class Examen_Compose extends Examen{

    private ArrayList<Examen> examenList = new ArrayList<>();

    public Examen_Compose(int no, String nom) {
        super(no,nom);
    }

    public  void addExamenChild(Examen examen) {
        examenList.add(examen);
    }
//    public ArrayList<Examen> getExamenList() {
//        return examenList;
//    }

    public void retirerExamen(Examen examen) {
        examenList.remove(examen);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Examen composite: " + nomExamen);
        for (Examen examen : examenList) {
            examen.afficherDetails();
        }
    }


    @Override
    public String getNomExamen() {
        return nomExamen;
    }

    @Override
    public boolean isExamenElementaire() {
        return false;
    }

    @Override
    public ArrayList<Examen> getComposantExamenList() {
        return examenList;
    }


    @Override
    public String paramsToString() {
        return "";
    }
}
