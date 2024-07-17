package org.system.SystemePrincipale.Ecouteurs.Services;


public class Cardiologie extends Service {
    private static final String NOM_SERVICE = "Cardiologie";

    public Cardiologie(String numTel, String courriel) {
        super(NOM_SERVICE, numTel, courriel);
    }


    public void handleNotification(String notification) {
        setNotification(notification);
        notifier();
    }

    @Override
    public void notifier() {
        panel.update(this);

    }


}
