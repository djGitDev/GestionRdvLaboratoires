package org.system.SystemePrincipale.Event;


public class ResultatExamenLaboratoire extends Event {
    private String codePatient;
    private int numeroRDV;
    private String dateExamen;
    private String uriDocument;

    // Constructeur
    public ResultatExamenLaboratoire(String codePatient, int numeroRDV, String dateExamen, String uriDocument) {
        super(Type.RESULTAT_EXAMEN);
        this.codePatient = codePatient;
        this.numeroRDV = numeroRDV;
        this.dateExamen = dateExamen;
        this.uriDocument = uriDocument;
    }

    public ResultatExamenLaboratoire() {
        super(Type.RESULTAT_EXAMEN);

    }

    // Getters et Setters
    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public int getNumeroRDV() {
        return numeroRDV;
    }

    public void setNumeroRDV(int numeroRDV) {
        this.numeroRDV = numeroRDV;
    }

    public String getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(String dateExamen) {
        this.dateExamen = dateExamen;
    }

    public String getUriDocument() {
        return uriDocument;
    }

    public void setUriDocument(String uriDocument) {
        this.uriDocument = uriDocument;
    }

    @Override
    public String toString() {
        return "ResultatExamenLaboratoire{" +
                "codePatient=" + codePatient +
                ", numeroRDV=" + numeroRDV +
                ", dateExamen='" + dateExamen + '\'' +
                ", uriDocument='" + uriDocument + '\'' +
                '}';
    }
}
