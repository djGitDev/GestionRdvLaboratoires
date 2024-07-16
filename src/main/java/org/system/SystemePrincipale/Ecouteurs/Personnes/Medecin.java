package org.system.SystemePrincipale.Ecouteurs.Personnes;


import org.system.SystemePrincipale.Ecouteurs.IListener;
import org.system.SystemePrincipale.Ecouteurs.Services.Service;
import org.system.SystemePrincipale.Vue.NotificationPanel;

public class Medecin extends Personne implements IListener {

    private int matricule;
    private Service serviceAssocie;
    private String notificationType;
    private String messageNotification;
    private NotificationPanel panel;

    public Medecin(String nom, String prenom, String numTel, String courriel, int matricule, Service service) {
        super(nom, prenom, numTel, courriel);
        this.matricule = matricule;
        this.serviceAssocie = service;
    }

    public int getMatricule() {
        return matricule;
    }


    public Service getServiceAssocie() {
        return serviceAssocie;
    }


    public void setPanel(NotificationPanel panel) {
        this.panel = panel;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public void handleNotification(String notification) {
        setNotification(notification);
        notifier();
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
