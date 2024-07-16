package org.system.SystemePrincipale.Vue;

import org.system.SystemePrincipale.Ecouteurs.IObservable;

public interface IObservateur {
    void update(IObservable observable);
}
