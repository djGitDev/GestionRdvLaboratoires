package org.system.SystemePrincipale.Ecouteurs;

public interface IListener extends IObservable {

    void handleNotification(String notification);
    void setNotificationType(String email);
}