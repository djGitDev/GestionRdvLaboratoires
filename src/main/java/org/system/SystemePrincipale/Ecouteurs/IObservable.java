package org.system.SystemePrincipale.Ecouteurs;

public interface IObservable {

    void notifier();
    String getNotification();
    void setNotification(String notification);

}
