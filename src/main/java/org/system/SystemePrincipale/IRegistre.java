package org.system.SystemePrincipale;

import org.system.SystemePrincipale.Ecouteurs.Personnes.IPatient;
import org.system.SystemePrincipale.Examen.Examen;

import java.util.ArrayList;

public interface IRegistre {
    public ArrayList<IPatient> examPatientList() ;
    public ArrayList<Examen> examenPrescritList(String codePatient);
}
