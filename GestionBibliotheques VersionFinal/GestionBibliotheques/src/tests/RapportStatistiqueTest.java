package tests;

import controller.RapportStatistiqueController;
import model.RapportStatistiqueInterface;
import model.RapportStatistiques;
import model.RapportStatistiquesModel;
import view.RapportStatistiqueView;

public class RapportStatistiqueTest {

	public static void main(String[] args) {
		// Initialisation du modèle (ici, RapportStatistiquesModel implémente
		// RapportStatistiqueInterface)
		RapportStatistiques model = new RapportStatistiques();
		// Initialisation de la vue
		RapportStatistiqueView view = new RapportStatistiqueView();

		// Initialisation du contrôleur
		RapportStatistiqueController controller = new RapportStatistiqueController(view, model);

		// Génération des rapports
		String rapportLivres = model.genererRapportLivresPlusEmpruntes("emprunts.csv");
		String rapportUtilisateurs = model.genererRapportUtilisateursPlusActifs("emprunts.csv");
		String rapportGeneral = model.genererRapportGeneral("emprunts.csv");

		// Affichage des rapports dans la vue
		view.setRapportText(rapportLivres);
		System.out.println("Rapport des livres les plus empruntés : \n" + rapportLivres);

		view.setRapportText(rapportUtilisateurs);
		System.out.println("Rapport des utilisateurs les plus actifs : \n" + rapportUtilisateurs);

		view.setRapportText(rapportGeneral);
		System.out.println("Rapport général : \n" + rapportGeneral);

		// Lancer la vue
		view.setVisible(true);
	}
}
