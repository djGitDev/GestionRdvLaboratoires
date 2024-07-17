package org.system.SystemePrincipale;

import java.util.ArrayList;

public interface IMediateur<T> {
    T traiterDemande(DemandeRDV demande);
    ArrayList<T> demandeTransfertResultat();
    ArrayList<DemandeRDV> extraireDemandes(Prescription prescription);
    void setNotificationType(String newType);
}
