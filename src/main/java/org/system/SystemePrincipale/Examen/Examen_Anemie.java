package org.system.SystemePrincipale.Examen;

import org.system.SystemePrincipale.DescriptionExamBuilder;
import org.system.SystemePrincipale.DescriptionExamen;
import org.system.SystemePrincipale.Registre;


public class Examen_Anemie extends Examen_Compose{
    private static Registre.Generateur generateur = Registre.Generateur.getInstance();

    public Examen_Anemie() {
        super(generateur.generate(), "Examen Anémie");
        DescriptionExamBuilder descAnalyseSang = new DescriptionExamBuilder("Analyse Sang");
        descAnalyseSang.ajouterParametre("FSC");
        DescriptionExamen analyseSang = descAnalyseSang.build();
        DescriptionExamBuilder descMyogamme = new DescriptionExamBuilder("Myélogramme");
        DescriptionExamen myogramme = descMyogamme.build();
        DescriptionExamBuilder descAnalyseUrinaire = new DescriptionExamBuilder("Analyse d'Urine");
        descAnalyseUrinaire.ajouterParametre("protéinurie");
        descAnalyseUrinaire.ajouterParametre("glycosurie");
        DescriptionExamen analyseUrinaire = descAnalyseUrinaire.build();

        addExamenChild(new Analyse_De_Sang(generateur.generate(), analyseSang));
        addExamenChild(new Myelogramme(generateur.generate(),myogramme));
        addExamenChild(new Analyse_Urine(generateur.generate(),analyseUrinaire));

    }
}
