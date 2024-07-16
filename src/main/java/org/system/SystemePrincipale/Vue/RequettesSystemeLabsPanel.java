package org.system.SystemePrincipale.Vue;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonObject;
import org.system.SystemePrincipale.Registre;
import org.system.SystemePrincipale.Prescription;
import org.system.SystemeGestionLabs.Controleurs.ControllerResultatExamen;
import org.system.SystemePrincipale.*;
//import org.system.SystemePrincipale.Centre.RegisterFile;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RequettesSystemeLabsPanel extends JPanel {

	private ControllerResultatExamen controllerEffectuationExamen = new ControllerResultatExamen();
	private IMediateur mediateur;

	private static final long serialVersionUID = 1L;
	private JTable demandeRDVtable;
	private JTable afficheResultatTable;
	private JPanel panel;
	private JButton attribuerRDVButton;
	private JButton faireExamenButton;
	private JButton transExamenButton;
	private JButton changeNotificationTypeButton;
	private JScrollPane scrollPaneRDV;
	private JScrollPane scrollPaneResultat;

	public RequettesSystemeLabsPanel(IGestionnaireRequetesSystemeGestionLabs gestionnaireRequeteSystemeLabs, NotificationPanel notificationPanel) {
		this.mediateur = new Mediateur(notificationPanel);
		setLayout(new GridLayout(3, 0));

		scrollPaneRDV = new JScrollPane();
		add(scrollPaneRDV);

		demandeRDVtable = new JTable();
		demandeRDVtable.setModel(buildRdvTableModel());
		scrollPaneRDV.setViewportView(demandeRDVtable);

		scrollPaneResultat = new JScrollPane();
		add(scrollPaneResultat);

		afficheResultatTable = new JTable();
		afficheResultatTable.setModel(buildResultExamsTableModel());
		scrollPaneResultat.setViewportView(afficheResultatTable);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new TitledBorder(null, "Traitements pr\u00E9d\u00E9finis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);

		attribuerRDVButton = new JButton("demande RDV");
		attribuerRDVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attributionRDV();
			}
		});
		panel.add(attribuerRDVButton);

		faireExamenButton = new JButton("Faire Examen");
		faireExamenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				effectuerExamens();
			}
		});
		panel.add(faireExamenButton);

		transExamenButton = new JButton("demande transfert resultats");
		transExamenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransmettreResultats();
			}
		});
		panel.add(transExamenButton);

		// Button to change notification type
		changeNotificationTypeButton = new JButton("Changer Type Notification");
		changeNotificationTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeNotificationType();
			}
		});
		panel.add(changeNotificationTypeButton);
	}

	private void changeNotificationType() {
		String[] options = {"email", "sms"};
		String newType = (String) JOptionPane.showInputDialog(this, "Select Notification Type:",
				"Notification Type", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (newType != null) {
			mediateur.setNotificationType(newType);
			JOptionPane.showMessageDialog(this, "Notification type changed to: " + newType);
		}
	}

	private DefaultTableModel buildRdvTableModel() {
		DefaultTableModel res = new DefaultTableModel();
		res.addColumn("Laboratoire");
		res.addColumn("Demande RDV");
		res.addColumn("Date RDV (JJ-MM-AAAA)");
		res.addColumn("Heure RDV ( HH:MI)");
		res.addColumn("Examen (Oui/Non)");
		return res;
	}

	private DefaultTableModel buildResultExamsTableModel() {
		DefaultTableModel res = new DefaultTableModel();
		res.addColumn("Code Patient");
		res.addColumn("Numero RDV");
		res.addColumn("Date Examen (JJ-MM-AAAA)");
		res.addColumn("URI Document");
		return res;
	}

	private void TransmettreResultats() {
		ArrayList<JsonObject> listResultat = mediateur.demandeTransfertResultat();
		DefaultTableModel tableRDV = (DefaultTableModel) afficheResultatTable.getModel();
		for (JsonObject resultat : listResultat) {
			tableRDV.addRow(new Object[]{
					resultat.get("codePatient").getAsString(),
					resultat.get("numeroRDV").getAsString(),
					resultat.get("dateExamen").getAsString(),
					resultat.get("uriDocument").getAsString()
			});
		}
	}

	private void attributionRDV() {
		DefaultTableModel tableRDV = (DefaultTableModel) demandeRDVtable.getModel();
		int nbreDemandeRDV = tableRDV.getRowCount();
		Registre registre = Registre.getInstance();
		DescriptionExamen description;
		ArrayList<Prescription> prescriptions = registre.getPrescriptions();
		for (Prescription prescription : prescriptions) {
			ArrayList<DemandeRDV> demandes = mediateur.extraireDemandes(prescription);
			for (DemandeRDV demande : demandes) {
				JsonObject jsonRDV = (JsonObject) mediateur.traiterDemande(demande);
				description = demande.getDescription();
				DataRDV demandeRdv = new DataRDV(demande.getNumDemande(), description, demande.getCodePatient());

				Object[] rowData = {
						jsonRDV.get("nomLabo").getAsString(),
						demandeRdv,
						jsonRDV.get("date").getAsString(),
						jsonRDV.get("heure").getAsString(),
						"non"
				};

				tableRDV.addRow(rowData);
			}
		}
	}

	private void effectuerExamens() {
		DefaultTableModel tableRDV = (DefaultTableModel) demandeRDVtable.getModel();
		if (tableRDV != null) {
			int nbreDemandeRDV = tableRDV.getRowCount();
			for (int i = 0; i < nbreDemandeRDV; i++) {
				if (tableRDV.getValueAt(i, 2) != null && tableRDV.getValueAt(i, 2).toString().length() != 0) {
					DataRDV rdv = (DataRDV) tableRDV.getValueAt(i, 1);
					String codePatient = rdv.getCodePatient();
					int numDemande = rdv.getNumDemande();
					DescriptionExamen descriptionExamen = rdv.getDescriptionExamen();
					controllerEffectuationExamen.effectuerExamen(numDemande, codePatient, descriptionExamen.getNom());
					tableRDV.setValueAt("Oui", i, 4);
				}
			}
		}
	}

	static class DataRDV {
		private int numDemande;
		private String codePatient;
		private DescriptionExamen descriptionExamen;

		public DataRDV(int numDemande, DescriptionExamen DescriptionExamen, String codePatient) {
			super();
			this.numDemande = numDemande;
			this.codePatient = codePatient;
			this.descriptionExamen = DescriptionExamen;
		}

		public int getNumDemande() {
			return numDemande;
		}

		public String getCodePatient() {
			return codePatient;
		}

		public DescriptionExamen getDescriptionExamen() {
			return descriptionExamen;
		}

		@Override
		public String toString() {
			return numDemande + " - " + descriptionExamen.getNom() + " [ codePatient #" + codePatient + "]";
		}
	}
}
