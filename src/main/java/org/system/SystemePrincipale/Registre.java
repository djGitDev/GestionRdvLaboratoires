package org.system.SystemePrincipale;

import org.system.SystemePrincipale.Examen.ConstanteExamen;
import org.system.SystemePrincipale.Examen.Examen;
import org.system.SystemePrincipale.Examen.Examen_Compose;
import org.system.SystemePrincipale.Examen.Examen_Unique;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Medecin;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Patient;
import org.system.SystemePrincipale.Ecouteurs.Personnes.IPatient;
import org.system.SystemePrincipale.Ecouteurs.Services.Cardiologie;
import org.system.SystemePrincipale.Ecouteurs.Services.Gastroenterologie;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;

import java.util.ArrayList;
import java.util.Hashtable;

public class Registre implements IRegistre {

	private ArrayList<IPatient> patientList;
	private Hashtable<Integer, Medecin> medecinList;
	private Hashtable<Integer, Service> serviceList;
	private Hashtable<String, ArrayList<Examen>> examemPrescritTable;
	private ArrayList<Prescription> prescriptions;

	private static Registre instance;

	// Constructeur privé pour empêcher l'instanciation depuis l'extérieur
	private Registre() {
		patientList = new ArrayList<>();
		examemPrescritTable = new Hashtable<>();
		defaultModelInitialisation();
	}

	// Méthode publique et statique pour obtenir l'unique instance
	public static synchronized Registre getInstance() {
		if (instance == null) {
			instance = new Registre();
		}
		return instance;
	}

	@Override
	public ArrayList<IPatient> examPatientList() {
		return patientList;
	}

	public Hashtable<Integer, Medecin> getMedecinList() {
		return medecinList;
	}

	public Hashtable<Integer, Service> getServiceList() {
		return serviceList;
	}

	@Override
	public ArrayList<Examen> examenPrescritList(String codePatient) {
		return examemPrescritTable.get(codePatient);
	}

	public ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void enregistrerMedecin(Medecin medecin) {
		this.medecinList.put(medecin.getMatricule(), medecin);
	}

	public Medecin getMedecin(int matricule) {
		return medecinList.get(matricule);
	}

	public void enregistrerPatient(Patient patient) {
		this.patientList.add(patient);
	}

	public Patient getPatient(String code) {
		for (IPatient patient : patientList) {
			if (patient.getCodePatient().equals(code)) {
				return (Patient) patient;
			}
		}
		return null; // or throw an exception if not found
	}

	/**
	 * Création des prescriptions d'examens fictifs
	 */
	public void defaultModelInitialisation() {
		Service cardiologie = new Cardiologie("5142001000", "cardiologie@service.com");
		Service gastroenterologie = new Gastroenterologie("5142001000", "gastroenterologie@service.com");
		Medecin medecin1 = new Medecin("Nom_Medecin1", "Prenom_Medecin1", "321-412-1111", "Medecin1@courriel.com", 1, cardiologie);
		Medecin medecin2 = new Medecin("Nom_Medecin2", "Prenom_Medecin2", "321-412-1111", "Medecin2@courriel.com", 2, gastroenterologie);
		IPatient p1 = new Patient("ALICE", "Prenom_patien1", 1, "514-514-1111", "Patien1@courriel.com", "01","email");
		IPatient p2 = new Patient("BOB", "Prenom_patien2", 2, "514-514-2222", "Patien2@courriel.com", "02","email");
		serviceList = new Hashtable<>();
		medecinList = new Hashtable<>();
		serviceList.put(medecin1.getMatricule(), cardiologie);
		serviceList.put(medecin2.getMatricule(), gastroenterologie);
		medecinList.put(medecin1.getMatricule(), medecin1);
		medecinList.put(medecin2.getMatricule(), medecin2);
		patientList.add(p1);
		patientList.add(p2);

		Generateur generateur = Generateur.getInstance();

		ArrayList<Examen> examensList1 = new ArrayList<>();
		Examen examen;

		DescriptionExamBuilder builder1 = new DescriptionExamBuilder(ConstanteExamen.ANALYSE_SANG);
		builder1.ajouterParametre(ConstanteExamen.FSC);
		builder1.ajouterParametre(ConstanteExamen.TSH);
		DescriptionExamen desc1 = builder1.build();
		examen = new Examen_Unique(generateur.generate(), ConstanteExamen.ANALYSE_SANG, desc1);
		examensList1.add(examen);
		IPatient P01 = patientList.get(0);
		Medecin MEDECIN1 = medecinList.get(P01.getCodeMedecin());
		Service SERVICE1 = serviceList.get(MEDECIN1.getMatricule());
		String codeP1 = P01.getCodePatient();
		Prescription presc = new Prescription(P01, MEDECIN1, SERVICE1, examen);
		prescriptions = new ArrayList<>();
		prescriptions.add(presc);

		DescriptionExamBuilder builder2 = new DescriptionExamBuilder(ConstanteExamen.Endoscopie);
		builder2.ajouterParametre(ConstanteExamen.OESOPHAGE);
		DescriptionExamen desc2 = builder2.build();
		examen = new Examen_Unique(generateur.generate(), ConstanteExamen.Endoscopie, desc2);
		examensList1.add(examen);

		P01 = patientList.get(0);
		MEDECIN1 = medecinList.get(P01.getCodeMedecin());
		SERVICE1 = serviceList.get(MEDECIN1.getMatricule());
		codeP1 = P01.getCodePatient();
		presc = new Prescription(P01, MEDECIN1, SERVICE1, examen);
		prescriptions.add(presc);

		DescriptionExamBuilder builder3 = new DescriptionExamBuilder(ConstanteExamen.Echographie);
		builder3.ajouterParametre(ConstanteExamen.OBSTETRICALE);
		DescriptionExamen desc3 = builder3.build();
		examen = new Examen_Unique(generateur.generate(), ConstanteExamen.Echographie, desc3);
		examensList1.add(examen);

		P01 = patientList.get(0);
		MEDECIN1 = medecinList.get(P01.getCodeMedecin());
		SERVICE1 = serviceList.get(MEDECIN1.getMatricule());
		codeP1 = P01.getCodePatient();
		presc = new Prescription(P01, MEDECIN1, SERVICE1, examen);
		prescriptions.add(presc);

		examemPrescritTable.putIfAbsent(codeP1, examensList1);
		// Examen du Patient 2
		ArrayList<Examen> examensList2 = new ArrayList<>();
		examen = new Examen_Compose(generateur.generate(), ConstanteExamen.ExamenThyroide);
		examensList2.add(examen);
		IPatient P02 = patientList.get(1);
		Medecin MEDECIN2 = medecinList.get(P02.getCodeMedecin());
		Service SERVICE2 = serviceList.get(MEDECIN2.getMatricule());
		String codeP2 = P02.getCodePatient();
		presc = new Prescription(P02, MEDECIN2, SERVICE2, examen);
		prescriptions.add(presc);

		Examen examenComposant;
		DescriptionExamBuilder builder4 = new DescriptionExamBuilder(ConstanteExamen.Echographie);
		builder4.ajouterParametre(ConstanteExamen.THYROIDE);
		DescriptionExamen desc4 = builder4.build();
		examenComposant = new Examen_Unique(generateur.generate(), ConstanteExamen.Echographie, desc4);
		((Examen_Compose) examen).addExamenChild(examenComposant);

		examenComposant = new Examen_Compose(generateur.generate(), ConstanteExamen.ExamenAnemie);
		((Examen_Compose) examen).addExamenChild(examenComposant);

		Examen examenComposant2;
		DescriptionExamBuilder builder5 = new DescriptionExamBuilder(ConstanteExamen.ANALYSE_SANG);
		builder5.ajouterParametre(ConstanteExamen.FSC);
		DescriptionExamen desc5 = builder5.build();
		examenComposant2 = new Examen_Unique(generateur.generate(), ConstanteExamen.ANALYSE_SANG, desc5);
		((Examen_Compose) examenComposant).addExamenChild(examenComposant2);

		DescriptionExamBuilder builder6 = new DescriptionExamBuilder(ConstanteExamen.Myelogramme);
		DescriptionExamen desc6 = builder6.build();
		examenComposant2 = new Examen_Unique(generateur.generate(), ConstanteExamen.Myelogramme, desc6);
		((Examen_Compose) examenComposant).addExamenChild(examenComposant2);

		DescriptionExamBuilder builder7 = new DescriptionExamBuilder(ConstanteExamen.AnalyseUrine);
		builder7.ajouterParametre(ConstanteExamen.PROTEINURE);
		builder7.ajouterParametre(ConstanteExamen.GLYCOSURIE);
		DescriptionExamen desc7 = builder7.build();
		examenComposant2 = new Examen_Unique(generateur.generate(), ConstanteExamen.AnalyseUrine, desc7);
		((Examen_Compose) examenComposant).addExamenChild(examenComposant2);

		DescriptionExamBuilder builder8 = new DescriptionExamBuilder(ConstanteExamen.ANALYSE_SANG);
		builder8.ajouterParametre(ConstanteExamen.TSH);
		DescriptionExamen desc8 = builder8.build();
		examenComposant = new Examen_Unique(generateur.generate(), ConstanteExamen.ANALYSE_SANG, desc8);
		((Examen_Compose) examen).addExamenChild(examenComposant);

		DescriptionExamBuilder builder9 = new DescriptionExamBuilder(ConstanteExamen.RadioIRM);
		builder9.ajouterParametre(ConstanteExamen.CERVEAU);
		DescriptionExamen desc9 = builder9.build();
		examen = new Examen_Unique(generateur.generate(), ConstanteExamen.RadioIRM, desc9);
		examensList2.add(examen);
		P02 = patientList.get(1);
		MEDECIN2 = medecinList.get(P02.getCodeMedecin());
		SERVICE2 = serviceList.get(MEDECIN2.getMatricule());
		codeP2 = P02.getCodePatient();
		presc = new Prescription(P02, MEDECIN2, SERVICE2, examen);
		prescriptions.add(presc);

		examemPrescritTable.putIfAbsent(codeP2, examensList2);
	}

	public static class Generateur {
		private int cpt = 0;
		private static Generateur instance;

		private Generateur() {
		}

		public static Generateur getInstance() {
			if (instance == null) {
				instance = new Generateur();
			}
			return instance;
		}

		public int generate() {
			cpt++;
			return cpt;
		}
	}
}
