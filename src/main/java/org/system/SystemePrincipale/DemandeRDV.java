package org.system.SystemePrincipale;

public class DemandeRDV {

    private static int noDemandeCompteur = 1;

    private int noDemande;
    private String codePatient;
    private DescriptionExamen descriptionExamen;
    private String date ;


    public DemandeRDV(String codePatient, DescriptionExamen description, String date) {
        this.noDemande = noDemandeCompteur++;
        this.codePatient = codePatient;
        this.descriptionExamen = description;
        this.date = date;
    }



    @Override
    public String toString() {
        return "DemandeRDV{" +
                "noDemande=" + noDemande +
                ", codePatient=" + codePatient +
                ", description=" + descriptionExamen +
                ", date='" + date + '\'' +
                '}';
    }
    public int getNumDemande() {
        return noDemande;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public DescriptionExamen getDescription() {
        return descriptionExamen;
    }


}
