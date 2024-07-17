package org.system.SystemePrincipale;

import org.system.SystemePrincipale.Examen.Examen;
import org.system.SystemePrincipale.Ecouteurs.Personnes.IPatient;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Medecin;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;

public class Prescription {
    private IPatient patient;
    private Medecin medecin;
    private Service service;
    private Examen examen;

    public Prescription(IPatient patient, Medecin medecin, Service service, Examen examen) {
        this.medecin = medecin;
        this.patient = patient;
        this.service = service;
        this.examen = examen;
    }



    public Examen getExam(){
        return examen;
    }

    public Medecin getMedecin(){
        return medecin;
    }

    public IPatient getPatient(){
        return patient;
    }

    public Service getCentreSoin(){
        return service;
    }

    //just for test function
    public void afficherPrescription() {
        System.out.println("Prescription pour le patient: " + patient);
        System.out.println("Prescrite par le médecin: " + medecin);
            examen.afficherDetails();

    }

    public Examen getExamen() {
        return examen;
    }
}
