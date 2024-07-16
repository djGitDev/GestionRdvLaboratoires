package org.system.SystemePrincipale.Notifieur;

import org.system.SystemePrincipale.Ecouteurs.IListener;
import org.system.SystemePrincipale.Event.Event;

import java.util.ArrayList;

public interface INotifieur {


    void notify(Event evenement);
}
