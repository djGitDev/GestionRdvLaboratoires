package org.system.SystemeGestionLabs;
import org.system.SystemePrincipale.DescriptionExamen;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ResultatExamen {

    private String codePatient;
    private int numeroRDV;
    private String dateExamen;
    private String uriDocument;

    // Constructeur
    public ResultatExamen(int numeroRDV,String codePatient, String dateExamen, String uriDocument) {
        this.codePatient = codePatient;
        this.numeroRDV = numeroRDV;
        this.dateExamen = dateExamen;
        this.uriDocument = uriDocument;
    }

    // Getters et Setters
    public String getCodePatient() {
        return codePatient;
    }


    public int getNumeroRDV() {
        return numeroRDV;
    }


    public String getDateExamen() {
        return dateExamen;
    }


    public String getUriDocument() {
        return uriDocument;
    }


    public static ResultatExamen effectuerExamen(int numDemande,String noPatient, String nomExam) {
        try {
            LocalDate aujourdhui = LocalDate.now();
            DateTimeFormatter formateur = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateFormatee = aujourdhui.format(formateur);
            String baseUri = "http://example.com/examen";
            String uriStr = String.format("%s?nom=%s&", baseUri, nomExam);
            URI examenURI = new URI(uriStr.replaceAll(" ", "%20"));
            return new ResultatExamen(numDemande,noPatient, dateFormatee, examenURI.toString());
        } catch (URISyntaxException e) {
            // Gérer l'exception ici
            System.err.println("Erreur lors de la génération de l'URI pour l'examen : " + e.getMessage());
            // Vous pouvez retourner un résultat par défaut ou null en cas d'erreur
            return null;
        }
    }
}
