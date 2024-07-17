package org.system.SystemePrincipale.Ecouteurs.Personnes;

public abstract class Personne {
    String nom;
    String prenom;
    String numTel;
    String courriel;

    Personne(String nom, String prenom, String numTel, String courriel) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.courriel = courriel;
    }

    public Personne(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
}
