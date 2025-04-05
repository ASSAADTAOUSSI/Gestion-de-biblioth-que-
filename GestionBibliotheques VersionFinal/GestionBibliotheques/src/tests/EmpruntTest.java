package tests;

import java.time.LocalDate;
import controller.EmpruntController;
import exceptions.EmpruntNotFoundException;
import model.Emprunt;
import model.EmpruntModel;
import model.LivreModel;
import view.EmpruntView;

public class EmpruntTest {
	public static void main(String[] args) throws EmpruntNotFoundException {
		// Initialisation du modèle, de la vue et du contrôleur
		String csvFilePath = "emprunts_test.csv"; // Fichier CSV pour les tests
		LivreModel livreModel = new LivreModel("Livre.csv");
		EmpruntModel model = new EmpruntModel(csvFilePath, livreModel);
		EmpruntView view = new EmpruntView();
		EmpruntController controller = new EmpruntController(model, view);

		// Nettoyage des anciens emprunts pour commencer avec un fichier propre
		model.lireCSV();
//        System.out.println("Initialisation complète du fichier de test.");
//
//        // === Test 1 : Ajouter un emprunt ===
//        System.out.println("=== Ajout d'un emprunt ===");
//        Emprunt emprunt1 = new Emprunt();
//        emprunt1.setIdUtilisateur(1);
//        emprunt1.setTitreLivre("Java for Beginners");
//        emprunt1.setDateEmprunt(LocalDate.of(2024, 1, 10));
//        emprunt1.setDateRetour(LocalDate.of(2024, 1, 20));
//        model.ajouterEmprunt(emprunt1);
//        afficherEmprunts(model);
//
//        // === Test 2 : Modifier un emprunt ===
//        System.out.println("=== Modification d'un emprunt ===");
//        model.modifierEmprunt(emprunt1.getIdEmprunt(), 1, "Advanced Java", 
//                              LocalDate.of(2024, 1, 15), LocalDate.of(2024, 2, 15));
//        afficherEmprunts(model);
//
//        // === Test 3 : Prolonger un emprunt ===
//        System.out.println("=== Prolongation d'un emprunt ===");
//        model.prolongerEmprunt(emprunt1.getIdEmprunt(), 7);
//        afficherEmprunts(model);
//
//        // === Test 4 : Rechercher un emprunt par titre ===
//        System.out.println("=== Recherche d'un emprunt par titre ===");
//        var empruntsTrouves = model.rechercherEmpruntParTitre("Advanced Java");
//        for (Emprunt e : empruntsTrouves) {
//            System.out.println(e);
//        }
//
//        // === Test 5 : Supprimer un emprunt ===
//        System.out.println("=== Suppression d'un emprunt ===");
//        model.supprimerEmprunt(emprunt1.getIdEmprunt());
//        afficherEmprunts(model);

		// Rendre la vue visible
		view.setVisible(true);
	}

	/**
	 * Affiche la liste des emprunts dans la console.
	 */
	private static void afficherEmprunts(EmpruntModel model) {
		System.out.println("Liste des emprunts :");
		for (Emprunt e : model.listerEmprunt()) {
			System.out.println(e);
		}
		System.out.println("-------------------------------");
	}
}
