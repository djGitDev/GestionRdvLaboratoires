package org.system.SystemePrincipale;


import java.util.ArrayList;

public class DescriptionExamen {

    private String nomExamen;
    private ArrayList<String> parametres;
    private String partieDuCorps;

    public DescriptionExamen(String nomExamen) {
        this.nomExamen = nomExamen;
    }

    public DescriptionExamen(DescriptionExamBuilder builder) {
        this.nomExamen = builder.getnomExamen();
        this.parametres = builder.getParametres();
        this.partieDuCorps = builder.getPartieDuCorps();
    }


    public ArrayList<String> getParametres() {
        return parametres;
    }
    public String getPartieDuCorps() {
        return partieDuCorps;
    }

    public String getNom() {
        return nomExamen;
    }

    public void afficherDetails() {
        System.out.println("Nom: " + nomExamen);
        System.out.println("Parametres: " + parametres);
        System.out.println("Partie du corps: " + partieDuCorps);

    }

    @Override
    public String toString() {
        return "DescriptionExamen{" +
                "nomExamen='" + nomExamen + '\'' +
                ", parametres=" + parametres +
                ", partieDuCorps='" + partieDuCorps + '\'' +
                '}';
    }

    public void ajouterParmetre(String parametre) {
        parametres.add(parametre);
    }
}
