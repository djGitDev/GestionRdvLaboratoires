package org.system.SystemePrincipale.Examen;

import org.system.SystemePrincipale.DescriptionExamBuilder;
import org.system.SystemePrincipale.DescriptionExamen;
import org.system.SystemePrincipale.Registre;

public class Examen_Thyroide extends Examen_Compose {
    private static Registre.Generateur generateur = Registre.Generateur.getInstance();


    public Examen_Thyroide() {
        super(generateur.generate(), "Examen Thyroïde");
        DescriptionExamBuilder descAnalyseSang = new DescriptionExamBuilder("Analyse Sang");
        descAnalyseSang.ajouterParametre("TSH");
        DescriptionExamen analyseSang = descAnalyseSang.build();
        DescriptionExamBuilder desEcho = new DescriptionExamBuilder("Échographie");
        DescriptionExamen echo = desEcho.build();
        addExamenChild(new Examen_Anemie());
        addExamenChild(new Analyse_De_Sang(generateur.generate(),analyseSang));
        addExamenChild(new Echographie(generateur.generate(),echo));
    }

}
