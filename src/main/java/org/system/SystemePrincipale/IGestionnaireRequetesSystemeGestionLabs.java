package org.system.SystemePrincipale;


import java.util.List;

public interface IGestionnaireRequetesSystemeGestionLabs<T> {

  T trouverRDV(DemandeRDV demande);
  void informerEcouteur(T RDV);
  T convertirDemande(DemandeRDV demande);
  List<T> demandeTransfertResultats();
}
