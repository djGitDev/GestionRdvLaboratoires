package org.system.SystemePrincipale.Ecouteurs.Services;


public class Gastroenterologie extends Service {
    private static final String NOM_SERVICE = "Gastroentérologie";
    private String messageNotification;

    public Gastroenterologie(String numTel, String courriel) {
        super(NOM_SERVICE, numTel, courriel);
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
