package tests;

import javax.swing.SwingUtilities;

import controller.UtilisateurController;
import model.Utilisateur;
import model.UtilisateurModel;
import view.UtilisateurFrame;

public class UserTest {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			UtilisateurModel model = new UtilisateurModel("users.csv");
			UtilisateurFrame view = null; // Déclarez la variable ici
			try {
				// Ajouter des utilisateurs prédéfinis
				model.ajouterUtilisateur(new Utilisateur("Meryem", "Meryem@gmail.com", "newPass123", "Admin"));
				model.ajouterUtilisateur(new Utilisateur("Sara", "Sara@gmail.com", "password", "Admin"));
				model.ajouterUtilisateur(new Utilisateur("Salma", "Salma@gmail.com", "admin123", "Admin"));
				model.ajouterUtilisateur(new Utilisateur("Bob", "bob@outlook.com", "password", "Membre"));

				// Initialisez la vue
				view = new UtilisateurFrame();
			} catch (Exception e) {
				System.err.println("Erreur : " + e.getMessage());
			}
			// Passez la vue et le modèle au contrôleur
			if (view != null) { // Vérifiez que view a été correctement initialisée
				new UtilisateurController(model, view);
			}
		});
	}
}
