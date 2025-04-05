package controller;

import model.*;
import view.*;
import exceptions.EmpruntNotFoundException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe EmpruntController. Cette classe agit comme un contrôleur dans
 * l'architecture MVC pour gérer les interactions entre le modèle (EmpruntModel)
 * et la vue (EmpruntView). Elle définit les actions pour les événements
 * déclenchés par l'interface utilisateur.
 */
public class EmpruntController {
	private EmpruntModel model;
	private EmpruntView view;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Constructeur de la classe EmpruntController.
	 * 
	 * @param model l'objet modèle pour gérer les données des emprunts.
	 * @param view  l'objet vue pour gérer l'interface utilisateur des emprunts.
	 */
	public EmpruntController(EmpruntModel model, EmpruntView view) {
		this.model = model;
		this.view = view;

		// Ajouter les écouteurs aux boutons de la vue
		view.getAjouterButton().addActionListener(new AjouterEmpruntListener());
		view.getModifierButton().addActionListener(new ModifierEmpruntListener());
		view.getSupprimerButton().addActionListener(new SupprimerEmpruntListener());
		view.getProlongerButton().addActionListener(new ProlongerEmpruntListener());
		view.getRechercherButton().addActionListener(new RechercherEmpruntListener());
		view.getAfficherButton().addActionListener(new AfficherEmpruntsListener());

		// Charger les emprunts existants au démarrage
		model.lireCSV();
		afficherTousEmprunts();
	}

	/**
	 * Méthode pour afficher tous les emprunts dans la table de la vue.
	 */
	private void afficherTousEmprunts() {
		view.getTableModel().setRowCount(0); // Vider la table
		for (Emprunt emprunt : model.listerEmprunt()) {
			view.ajouterEmpruntDansTable(emprunt.getIdEmprunt(), emprunt.getIdUtilisateur(), emprunt.getTitreLivre(),
					emprunt.getDateEmprunt(), emprunt.getDateRetour());
		}
	}

	/**
	 * Classe interne pour gérer l'ajout d'un emprunt.
	 */
	class AjouterEmpruntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int idUser = Integer.parseInt(view.getIdUserField().getText());
				String titre = view.getTitreLivreField().getText();
				LocalDate dateEmprunt = LocalDate.parse(view.getDateEmpruntField().getText(), formatter);
				LocalDate dateRetour = LocalDate.parse(view.getDateRetourField().getText(), formatter);

				Emprunt emprunt = new Emprunt();
				emprunt.setIdUtilisateur(idUser);
				emprunt.setTitreLivre(titre);
				emprunt.setDateEmprunt(dateEmprunt);
				emprunt.setDateRetour(dateRetour);

				model.ajouterEmprunt(emprunt);
				view.ajouterEmpruntDansTable(emprunt.getIdEmprunt(), idUser, titre, dateEmprunt, dateRetour);

				JOptionPane.showMessageDialog(view, "Emprunt ajouté avec succès !");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout de l'emprunt : " + ex.getMessage(),
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Classe interne pour gérer la modification d'un emprunt.
	 */
	class ModifierEmpruntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'emprunt à modifier :"));
				int idUser = Integer.parseInt(view.getIdUserField().getText());
				String titre = view.getTitreLivreField().getText();
				LocalDate dateEmprunt = LocalDate.parse(view.getDateEmpruntField().getText(), formatter);
				LocalDate dateRetour = LocalDate.parse(view.getDateRetourField().getText(), formatter);

				model.modifierEmprunt(id, idUser, titre, dateEmprunt, dateRetour);
				afficherTousEmprunts();
				JOptionPane.showMessageDialog(view, "Emprunt modifié avec succès !");
			} catch (EmpruntNotFoundException ex) {
				JOptionPane.showMessageDialog(view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erreur lors de la modification : " + ex.getMessage(), "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Classe interne pour gérer la suppression d'un emprunt.
	 */
	class SupprimerEmpruntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'emprunt à supprimer :"));
				model.supprimerEmprunt(id);
				afficherTousEmprunts();
				JOptionPane.showMessageDialog(view, "Emprunt supprimé avec succès !");
			} catch (EmpruntNotFoundException ex) {
				JOptionPane.showMessageDialog(view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erreur lors de la suppression : " + ex.getMessage(), "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Classe interne pour gérer la prolongation d'un emprunt.
	 */
	class ProlongerEmpruntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'emprunt à prolonger :"));
				int joursSupp = Integer.parseInt(view.getJoursSuppField().getText());

				model.prolongerEmprunt(id, joursSupp);
				afficherTousEmprunts();
				JOptionPane.showMessageDialog(view, "Emprunt prolongé avec succès !");
			} catch (EmpruntNotFoundException ex) {
				JOptionPane.showMessageDialog(view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erreur lors de la prolongation : " + ex.getMessage(), "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Classe interne pour gérer la recherche d'emprunts par titre.
	 */
	class RechercherEmpruntListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String titre = view.getRechercherField().getText();
			ArrayList<Emprunt> resultats = model.rechercherEmpruntParTitre(titre);

			view.getTableModel().setRowCount(0); // Vider la table
			for (Emprunt emprunt : resultats) {
				view.ajouterEmpruntDansTable(emprunt.getIdEmprunt(), emprunt.getIdUtilisateur(),
						emprunt.getTitreLivre(), emprunt.getDateEmprunt(), emprunt.getDateRetour());
			}
			if (resultats.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Aucun emprunt trouvé avec ce titre.");
			}
		}
	}

	/**
	 * Classe interne pour afficher tous les emprunts.
	 */
	class AfficherEmpruntsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			afficherTousEmprunts();
		}
	}
}
