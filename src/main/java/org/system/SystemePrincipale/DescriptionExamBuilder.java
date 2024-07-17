package org.system.SystemePrincipale;


import java.util.ArrayList;

public class DescriptionExamBuilder {

    private String nomExamen;
    private ArrayList<String> parametres;
    private String partieDuCorps;

    public DescriptionExamBuilder(String nomExamen) {
        this.nomExamen = nomExamen;
        this.parametres = new ArrayList<>();

    }
    //parametres optionnels avec nombre variable
    public DescriptionExamBuilder ajouterPartieDuCorps(String partieDuCorps) {
        this.partieDuCorps = partieDuCorps;
        return this;
    }

    public DescriptionExamBuilder ajouterParametre(String parametre) {
        this.parametres.add(parametre);
        return this;
    }
    public DescriptionExamen build(){
        return new DescriptionExamen(this);
    }

    public String getnomExamen() {
        return nomExamen;
    }

    public ArrayList<String> getParametres() {
        return parametres;
    }

    public String getPartieDuCorps() {
        return partieDuCorps;
    }
}
