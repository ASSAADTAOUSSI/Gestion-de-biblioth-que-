package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.Utilisateur;
import model.UtilisateurModel;
import view.UtilisateurFrame;

public class UtilisateurController {
	private UtilisateurModel model;
	private UtilisateurFrame view;

	public UtilisateurController(UtilisateurModel model, UtilisateurFrame view) {
		this.model = model;
		this.view = view;

		// Ajouter les listeners pour les boutons
		view.getAjouterButton().addActionListener(e -> ajouterUtilisateur());
		view.getModifierButton().addActionListener(e -> modifierUtilisateur());
		view.getSupprimerButton().addActionListener(e -> supprimerUtilisateur());
		view.getRechercherButton().addActionListener(e -> rechercherUtilisateur());
		view.getResetButton().addActionListener(e -> {
			view.getIdTextField().setText("");
			view.getNomTextField().setText("");
			view.getEmailTextField().setText("");
			view.getMotDePasseTextField().setText("");
			view.getRoleComboBox().setSelectedIndex(0);
		});
		// Charger les données initiales
		rechargerTable();
	}

	private void ajouterUtilisateur() {
		try {
			String nom = view.getNomTextField().getText();
			String email = view.getEmailTextField().getText();
			String motDePasse = view.getMotDePasseTextField().getText();
			String role = (String) view.getRoleComboBox().getSelectedItem();

			if (nom.isEmpty() || motDePasse.isEmpty() || email.isEmpty()) {
				throw new IllegalArgumentException("Les champs Nom et Mot de Passe ne peuvent pas être vides.");
			} else if (!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
				throw new IllegalArgumentException("L'email doit être au format 'exemple@domaine.com'.");
			}
			Utilisateur utilisateur = new Utilisateur(nom, email, motDePasse, role);
			model.ajouterUtilisateur(utilisateur);

			rechargerTable();
			JOptionPane.showMessageDialog(view, "Utilisateur ajouté avec succès !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void modifierUtilisateur() {
		try {
			int id = Integer.parseInt(view.getIdTextField().getText());
			String nom = view.getNomTextField().getText();
			String email = view.getEmailTextField().getText();
			String motDePasse = view.getMotDePasseTextField().getText();
			String role = (String) view.getRoleComboBox().getSelectedItem();

			if (nom.isEmpty() || motDePasse.isEmpty() || email.isEmpty()) {
				throw new IllegalArgumentException("Les champs Nom et Mot de Passe ne peuvent pas être vides.");
			} else if (!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
				throw new IllegalArgumentException("L'email doit être au format 'exemple@domaine.com'.");
			}
			model.modifierUtilisateur(id, nom, email, motDePasse, role);
			rechargerTable();
			JOptionPane.showMessageDialog(view, "Utilisateur modifié avec succès !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void supprimerUtilisateur() {
		try {
			int id = Integer.parseInt(view.getIdTextField().getText());
			model.supprimerUtilisateur(id);
			rechargerTable();
			JOptionPane.showMessageDialog(view, "Utilisateur supprimé avec succès !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rechercherUtilisateur() {
		try {
			String critere = view.getRechercheTextField().getText();
			if (critere.isEmpty()) {
				rechargerTable();
				return;
			}

			List<Utilisateur> resultatRecherche = model.getListe().stream()
					.filter(u -> String.valueOf(u.getId()).contains(critere)
							|| u.getNom().toLowerCase().contains(critere.toLowerCase())
							|| u.getRole().toLowerCase().contains(critere.toLowerCase()))
					.collect(Collectors.toList());

			Object[][] donnees = resultatRecherche.stream()
					.map(u -> new Object[] { u.getId(), u.getNom(), u.getEmail(), u.getMotDePasse(), u.getRole() })
					.toArray(Object[][]::new);

			view.rechargerTable(donnees);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erreur lors de la recherche : " + e.getMessage(), "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rechargerTable() {
		List<Utilisateur> utilisateurs = model.getListe();
		System.out.println("Utilisateurs dans la liste : " + utilisateurs); // Pour déboguer

		Object[][] donnees = utilisateurs.stream()
				.map(u -> new Object[] { u.getId(), u.getNom(), u.getEmail(), u.getMotDePasse(), u.getRole() })
				.toArray(Object[][]::new);
		view.rechargerTable(donnees);
	}
}
