package org.system.SystemePrincipale.Ecouteurs.Services;

import org.system.SystemePrincipale.Ecouteurs.IListener;
import org.system.SystemePrincipale.Vue.NotificationPanel;

public abstract class Service implements IListener {

    protected String nomService;
    protected String numTel;
    protected String courriel;
    protected String notificationType;
    protected NotificationPanel panel;
    protected String messageNotification;

    Service(String nomService, String numTel, String courriel) {
        this.nomService = nomService;
        this.numTel = numTel;
        this.courriel = courriel;
    }


    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public NotificationPanel getPanel() {
        return panel;
    }

    public void setPanel(NotificationPanel panel) {
        this.panel = panel;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType)
    {
        this.notificationType = notificationType;
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