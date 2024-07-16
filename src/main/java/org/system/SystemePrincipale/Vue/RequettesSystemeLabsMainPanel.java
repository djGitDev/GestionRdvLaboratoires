package org.system.SystemePrincipale.Vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import com.google.gson.JsonObject;
import org.system.SystemePrincipale.IGestionnaireRequetesSystemeGestionLabs;
import java.awt.FlowLayout;



/**
 * Panneau principale de l'interface graphique.
 * Il contient tous les autres panneaux
 */
public class RequettesSystemeLabsMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RequettesSystemeLabsPanel requettesSystemeLabsPanel;
	private NotificationPanel notificationPanel;
	/**
	 * Create the panel.
	 */
	public RequettesSystemeLabsMainPanel(IGestionnaireRequetesSystemeGestionLabs<JsonObject> controllerPriseRdv, NotificationPanel notificationPanel) {
		this.notificationPanel = notificationPanel;  // Assign the existing notificationPanel
		setLayout(new BorderLayout(0, 0));
		requettesSystemeLabsPanel = new RequettesSystemeLabsPanel(controllerPriseRdv, notificationPanel);

		add(requettesSystemeLabsPanel, BorderLayout.CENTER);

		// Create a new panel to hold notificationPanel and other controls
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(notificationPanel, BorderLayout.CENTER);

		JPanel controlLabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(controlLabPanel, BorderLayout.SOUTH);

		add(bottomPanel, BorderLayout.SOUTH);
	}
}
