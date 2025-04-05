package tests;

import controller.*;
import model.*;
import view.RetourView;

public class RetourTest {
	public static void main(String[] args) {
		// Chemin vers le fichier CSV

		// Créer le modèle des retours
		RetourModel retourModel = new RetourModel("retours.csv");

		// Charger les emprunts depuis le fichier CSV
		retourModel.chargerDepuisEmprunts("emprunts.csv");

		// Créer l'interface utilisateur
		RetourView frame = new RetourView();

		// Connecter le contrôleur
		RetourController controller = new RetourController(frame, retourModel);

		// Mettre à jour la table avec les données des retours
		controller.updateTableData();
		// Afficher la fenêtre
		frame.setVisible(true);
	}
}
