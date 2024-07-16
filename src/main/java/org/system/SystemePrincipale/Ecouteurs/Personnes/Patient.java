package org.system.SystemePrincipale.Ecouteurs.Personnes;

import org.system.SystemePrincipale.Ecouteurs.IListener;
import org.system.SystemePrincipale.Vue.NotificationPanel;

public class Patient extends Personne implements IPatient, IListener {
    String code;
    int codeMedecin;
    private String messageNotification;
    private NotificationPanel panel;
    private String notificationType;

    public Patient(String nom, String prenom, int codeMedecin, String numTel, String courriel, String code, String notificationType) {
        super(nom, prenom, numTel, courriel);
        this.code = code;
        this.codeMedecin = codeMedecin;
//        this.notificationType = notificationType;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    @Override
    public String getCodePatient() {
        return code;
    }

    @Override
    public String getNomPatient() {
        return nom;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + nom;
    }

    public void setPanel(NotificationPanel panel) {
        this.panel = panel;
    }

    public NotificationPanel getPanel() {
        return panel;
    }

    @Override
    public void handleNotification(String notification) {
        setNotification(notification);
        notifier();
//        INotifieur notifieur = new NotifieurPatient();
//        notifieur.envoyerNotification(notificationType.toUpperCase() + " " + notification, courriel, panel);
    }

    



    @Override
    public void notifier() {
        panel.update(this);

    }

    @Override
    public String getNotification() {
        return messageNotification;
    }

    @Override
    public void setNotification(String notification) {
        messageNotification = notification;
    }

}
