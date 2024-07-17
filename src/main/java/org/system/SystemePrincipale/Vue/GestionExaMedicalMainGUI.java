package org.system.SystemePrincipale.Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.google.gson.JsonObject;
import org.system.SystemePrincipale.IRegistre;
import org.system.SystemePrincipale.Registre;
import org.system.SystemePrincipale.IGestionnaireRequetesSystemeGestionLabs;
import org.system.SystemePrincipale.GestionnaireRequetesSystemeGestionLabsJSON;


/**
 * 
 * Classe principale qui implémente l'interface graphique et fait la création 
 * d'un modèle de données fictif. 
 *    
 * Il y a une autre classe principale () dans laquel il faut créer les objets de 
 * votre modèle   
 * 
 */
public class GestionExaMedicalMainGUI {

	private JFrame frame;
	private RequettesSystemeLabsMainPanel requettesSystemeLabsMainPanel;
	private NotificationPanel notificationPanel;
	private ExaMedicalPrescritPanel exaMedPrescritPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionExaMedicalMainGUI window = new GestionExaMedicalMainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionExaMedicalMainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 * 
	 * Fait la création des objets racines du domaine que l'UI utilise.
	 * 
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		IRegistre examHandler = Registre.getInstance();
		
		
		exaMedPrescritPanel = new ExaMedicalPrescritPanel(examHandler);
		frame.getContentPane().add(exaMedPrescritPanel, BorderLayout.WEST);

        JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.35);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		IGestionnaireRequetesSystemeGestionLabs<JsonObject> controllerPriseRdv = new GestionnaireRequetesSystemeGestionLabsJSON();

		notificationPanel = new NotificationPanel();  // Initialize the notification panel
		requettesSystemeLabsMainPanel = new RequettesSystemeLabsMainPanel(controllerPriseRdv, notificationPanel);
		splitPane.setLeftComponent(requettesSystemeLabsMainPanel);

		splitPane.setRightComponent(notificationPanel);
		
		notificationPanel.ajouteNotificationMsgMedecin("Ici, affichage des notifications pour le médecin" );
		notificationPanel.ajouteNotificationMsgService("Ici, affichage des notifications pour le Service" );
		notificationPanel.ajouteNotificationMsgPatient("Ici, affichage des notifications pour le patient" );		
		
	}

}
