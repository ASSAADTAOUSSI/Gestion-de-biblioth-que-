package controller;

import model.RapportStatistiques;
import view.RapportStatistiqueView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RapportStatistiqueController {
	private RapportStatistiqueView vue;
	private RapportStatistiques rapportStatistique;

	public RapportStatistiqueController(RapportStatistiqueView vue, RapportStatistiques rapportStatistique) {
		this.vue = vue;
		this.rapportStatistique = rapportStatistique;

		// Ajouter un listener au bouton pour générer le rapport
		vue.addGenererRapportListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				genererRapport();
			}
		});
	}

	// Méthode pour générer le rapport
	private void genererRapport() {
		try {
			// Générer les rapports
			String rapportLivres = rapportStatistique.genererRapportLivresPlusEmpruntes("./data/emprunts.csv");
			String rapportUtilisateurs = rapportStatistique.genererRapportUtilisateursPlusActifs("./data/emprunts.csv");
			String rapportGeneral = rapportStatistique.genererRapportGeneral("./data/emprunts.csv");

			// Afficher le rapport général dans la vue
			vue.setRapportText(rapportGeneral);
		} catch (Exception e) {
			vue.setRapportText("Erreur lors de la génération du rapport : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
