package tests;

import controller.*;
import model.*;
import view.*;

import javax.swing.*;

public class MainTest {
	public static void main(String[] args) {
		try {
			// Initialisation des modèles avec des chemins vers des fichiers CSV valides
			LivreModel livreModel = new LivreModel("./data/livres.csv");
			UtilisateurModel utilisateurModel = new UtilisateurModel("./data/utilisateurs.csv");
			EmpruntModel empruntModel = new EmpruntModel("./data/emprunts.csv", livreModel);
			RetourModel retourModel = new RetourModel("./data/retour.csv");
			RapportStatistiquesModel rapportModel = new RapportStatistiquesModel();
			RapportStatistiques rapport = new RapportStatistiques();

			livreModel.lireCSV();
			utilisateurModel.lireCSV();
			empruntModel.lireCSV();
			retourModel.lireCSV();

			// Initialisation des vues
			LivreView livreView = new LivreView();
			UtilisateurFrame utilisateurView = new UtilisateurFrame();
			EmpruntView empruntView = new EmpruntView();
			retourModel.chargerDepuisEmprunts("./data/emprunts.csv");
			RetourView retourView = new RetourView();
			RapportStatistiqueView rapportView = new RapportStatistiqueView();

			// Initialisation des contrôleurs avec modèles et vues
			LivreController livreController = new LivreController(livreModel, livreView);
			UtilisateurController utilisateurController = new UtilisateurController(utilisateurModel, utilisateurView);
			EmpruntController empruntController = new EmpruntController(empruntModel, empruntView);
			RetourController retourController = new RetourController(retourView, retourModel);
			RapportStatistiqueController rapportController = new RapportStatistiqueController(rapportView, rapport);

			// Création de la vue principale (MainView)
			MainView mainView = new MainView();

			// Ajout des onglets avec vérification
			addTabSafely(mainView, "Livres", livreView.getMainPanel());
			addTabSafely(mainView, "Utilisateurs", utilisateurView.getMainPanel());
			addTabSafely(mainView, "Emprunts", empruntView.getMainPanel());
			addTabSafely(mainView, "Retours", retourView.getMainPanel());
			addTabSafely(mainView, "Rapports", rapportView.getMainPanel());

			livreModel.sauvegarderCSV();
			utilisateurModel.sauvegarderCSV();
			empruntModel.sauvegarderCSV();
			retourModel.sauvegarderCSV();

			// Rendre l'interface visible
			mainView.setVisible(true);

		} catch (Exception e) {
			// Gestion d'erreurs générales
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Une erreur s'est produite : " + e.getMessage(), "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Méthode utilitaire pour ajouter un onglet à la vue principale avec
	 * vérification.
	 */
	private static void addTabSafely(MainView mainView, String title, JPanel panel) {
		if (panel != null) {
			mainView.addTab(title, panel);
		} else {
			System.err.println("Impossible d'ajouter l'onglet '" + title + "' car le panneau est null.");
		}
	}
}
