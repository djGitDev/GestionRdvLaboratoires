package org.system.SystemeGestionLabs;

public class Laboratoire {

    private String code;
    private String nom;
    private String adresse;
    private String noTel;
    private String email;


    public Laboratoire(String nom,String code) {
        this.code = code;
        this.nom = nom;
    }




    public String getAdresse() {
        return adresse;
    }

    public String getNoTel() {
        return noTel;
    }

    public String getEmail() {
        return email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
