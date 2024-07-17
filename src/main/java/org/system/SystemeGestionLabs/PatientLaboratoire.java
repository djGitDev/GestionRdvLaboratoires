package org.system.SystemeGestionLabs;

public class PatientLaboratoire {

    private int noPatient;
    private String nomPatient;
    private String noTel;
    private String email;

    public PatientLaboratoire(int noPatient, String nomPatient) {
        this.noPatient = noPatient;
        this.nomPatient = nomPatient;

    }
    public void ajouterNoTel(String noTel) {
        this.noTel = noTel;
    }

    public void ajouterEmail(String email) {
        this.email = email;
    }

    public int getNoPatient() {
        return noPatient;
    }
}


