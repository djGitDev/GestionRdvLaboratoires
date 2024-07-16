package org.system.SystemePrincipale.Vue;

import org.system.SystemePrincipale.Examen.Examen;
import org.system.SystemePrincipale.IRegistre;
import org.system.SystemePrincipale.Ecouteurs.Personnes.IPatient;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Implémente la panneau qui affiche la liste des examens prescrits pour chaque Patient
 *
 *
 */
@SuppressWarnings("serial")
public class ExaMedicalPrescritPanel extends JPanel {

	private IRegistre examenHandler ;
	private JButton demandeRDVButton;

	/**
	 * Create the panel.
	 */
	public ExaMedicalPrescritPanel( IRegistre examenHandler ) {
		this.examenHandler  = examenHandler ;
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		JTree tree = new JTree(construireModel());
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}

		scrollPane.setViewportView(tree);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.SOUTH);

		demandeRDVButton = new JButton("Demandez les RDV");
		demandeRDVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generationDemandesRDV() ;
			}
		});
		panel.add(demandeRDVButton);
	}

	/**
	 * Construit le modèle de données de l'arbre (JTree) : les noeuds
	 *
	 * @return
	 */
	private DefaultTreeModel construireModel() {


		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new ExamenNode(null)) ;
		DefaultTreeModel model = new DefaultTreeModel(root) ;

		// parcours des patients
		if (examenHandler != null) {
			ArrayList<IPatient> patientList = examenHandler.examPatientList() ;
			for (IPatient unPatient : patientList) {
				System.out.println ("Un Patient : " + unPatient.getCodePatient() +
						"Nom : " + unPatient.getNomPatient()) ;

				DefaultMutableTreeNode patientNode = new DefaultMutableTreeNode(new ExamenNode(unPatient)) ;

				root.add(patientNode);

				ArrayList<Examen> examenList = examenHandler.examenPrescritList(unPatient.getCodePatient()) ;
				for (Examen unExamen : examenList) {
					System.out.println ("Un Examen : " + unExamen.getNomExamen()) ;
					DefaultMutableTreeNode examenNode = new DefaultMutableTreeNode(new ExamenNode(unExamen)) ;
					patientNode.add(examenNode);

					if (!unExamen.isExamenElementaire()) {
						populateNode(examenNode , unExamen) ;
					}
				}
			}
		}

		return model ;
	}

	/**
	 *
	 * @param examenNode
	 * @param examenMedical
	 */
	private static void populateNode(DefaultMutableTreeNode examenNode ,Examen examenMedical) {
		ArrayList<Examen> examList = examenMedical.getComposantExamenList() ;

		for (Examen unExamen : examList) {
			DefaultMutableTreeNode examenChildNode = new DefaultMutableTreeNode(new ExamenNode(unExamen)) ;
			examenNode.add(examenChildNode);

			if (!unExamen.isExamenElementaire()) {
				populateNode(examenChildNode , unExamen ) ;
			}
		}
	}

	/**
	 * Callback du bouton Demande de RDV
	 * Doit lancer la méthode de votre modèle qui génére les demandes de RDV
	 *
	 */
	private void generationDemandesRDV() {
		System.err.println (this.getClass().getSimpleName() + "::generationDemandesRDV() Not Implémented");
	}

}
// ============================================================================
/**
 * Contient les données d'un noeud du JTree
 */
class ExamenNode {
	private Object dataNode ;


	public ExamenNode(Object data ) {
		this.dataNode = data;
	}

	@Override
	public String toString() {

		String res = null ;

		if (dataNode != null) {
			if (dataNode instanceof Examen) {
				res = ((Examen)dataNode).getNomExamen() ;

				res += " " + ((Examen)dataNode).paramsToString();

			} else if (dataNode instanceof IPatient) {
				res = ((IPatient)dataNode).getNomPatient() ;
			}

		} else {
			res = "Centre_Soin" ;
		}
		return res ;
	}
}


