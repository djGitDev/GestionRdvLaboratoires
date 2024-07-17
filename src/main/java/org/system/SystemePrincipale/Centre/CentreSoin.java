package org.system.SystemePrincipale.Centre;

import org.system.SystemePrincipale.Registre;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Medecin;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Patient;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;


import java.util.ArrayList;
import java.util.List;

public class CentreSoin {
    private String nom;
    private List<Service> services;
    private Registre registre;

    public CentreSoin(String nom) {
        this.nom = nom;
        this.services = new ArrayList<>();
        this.registre = Registre.getInstance();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   public List<Service> getServices() {
        return services;
    }

    public void ajouterService(Service service) {
        this.services.add(service);
    }

    public void enregistrerMedecin(Medecin medecin) {
        this.registre.enregistrerMedecin(medecin);
    }

    public Medecin getMedecin(int matricule) {
        return this.registre.getMedecin(matricule);
    }

    public void enregistrerPatient(Patient patient) {
        this.registre.enregistrerPatient(patient);
    }

    public Patient getPatient(String code) {
        return this.registre.getPatient(code);
    }


}
