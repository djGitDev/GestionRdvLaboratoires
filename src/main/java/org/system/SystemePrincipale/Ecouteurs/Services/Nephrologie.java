package org.system.SystemePrincipale.Ecouteurs.Services;

import org.system.SystemePrincipale.Vue.NotificationPanel;

public class Nephrologie extends Service {
    private static final String NOM_SERVICE = "Néphrologie";
    private String messageNotification;

    public Nephrologie(String numTel, String courriel, NotificationPanel panel, String notificationType) {
        super(NOM_SERVICE, numTel, courriel);
        setPanel(panel);
        setNotificationType(notificationType);
    }

    public void handleNotification(String notification) {
        setNotification(notification);
        notifier();

        //        INotifieur notifieur = new NotifieurService();
//        System.out.println("notifiyiung th service");
//        notifieur.envoyerNotification(getNotificationType() + notification, getCourriel(),getPanel());
    }


    @Override
    public void notifier() {
        panel.update(this);

    }


}