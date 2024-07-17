package org.system.SystemePrincipale.Vue;

import org.system.SystemePrincipale.Ecouteurs.IObservable;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Medecin;
import org.system.SystemePrincipale.Ecouteurs.Personnes.Patient;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

/**
 * Implémente le panneau qui contient les zones de texte pour les messages
 * de notifications
 *
 */
public class NotificationPanel extends JPanel implements IObservateur {
	@Override
	public void update(IObservable observable) {
		if(observable instanceof Patient) {
			ajouteNotificationMsgPatient(observable.getNotification());
		}else if(observable instanceof Medecin) {
			ajouteNotificationMsgMedecin(observable.getNotification());
		} else if (observable instanceof Service) {
			ajouteNotificationMsgService(observable.getNotification());
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextArea notifMedecinTextArea;
	private JTextArea notifPatientTextArea;
	private JTextArea notifServiceTextArea;

	/**
	 * Create the panel.
	 */
	public NotificationPanel() {
		setLayout(new BorderLayout(0, 0));

		notifMedecinTextArea = new JTextArea();
		notifMedecinTextArea.setEditable(false);
		notifMedecinTextArea.setBorder(new TitledBorder("Notification Medecin"));
		JScrollPane medecinScrollPane = new JScrollPane(notifMedecinTextArea);

		add(medecinScrollPane, BorderLayout.NORTH);

		notifPatientTextArea = new JTextArea();
		notifPatientTextArea.setEditable(false);
		notifPatientTextArea.setBorder(new TitledBorder("Notification Patient"));
		JScrollPane patientScrollPane = new JScrollPane(notifPatientTextArea);

		add(patientScrollPane, BorderLayout.CENTER);

		notifServiceTextArea = new JTextArea();
		notifServiceTextArea.setEditable(false);
		notifServiceTextArea.setBorder(new TitledBorder("Notification Service"));
		JScrollPane serviceScrollPane = new JScrollPane(notifServiceTextArea);

		add(serviceScrollPane, BorderLayout.SOUTH);
	}

	/**
	 * Ajoute un message (à la fin) dans le panneau des notification pour le médecin
	 *
	 * @param msg
	 */
	public void ajouteNotificationMsgMedecin(String msg) {
		notifMedecinTextArea.append(msg + "\n");
	}

	/**
	 * Ajoute un message (à la fin) dans le panneau des notification pour le patient
	 *
	 * @param msg
	 */
	public void ajouteNotificationMsgPatient(String msg) {
		notifPatientTextArea.append(msg + "\n");
	}

	/**
	 * Ajoute un message (à la fin) dans le panneau des notification pour le service
	 *
	 * @param msg
	 */
	public void ajouteNotificationMsgService(String msg) {
		notifServiceTextArea.append(msg + "\n");
	}

}
