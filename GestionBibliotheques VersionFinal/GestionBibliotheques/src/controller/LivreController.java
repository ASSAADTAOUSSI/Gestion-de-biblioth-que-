package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Livre;
import model.LivreModel;
import view.LivreView;

public class LivreController {
	private LivreModel model;
	private LivreView view;

	public LivreController(LivreModel model) {
		this.model = model;
	}

	public LivreController(LivreModel model, LivreView view) {
		this.model = model;
		this.view = view;

		// Initialiser les événements
		initialiserController();
	}

	public void initialiserController() {
		view.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterLivre();
			}
		});

		view.getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerLivre();
			}
		});

		view.getModifyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierLivre();
			}
		});

		view.getResetButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reinitialiserForm();
			}
		});

		view.getSearchButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rechercherLivre();
			}

		});

		// Charger la liste des livres au démarrage
		afficherLaListeDesLivres();
	}

	public void ajouterLivre() {
		try {
			String titre = view.getTitreField().getText();
			String auteur = view.getAuteurField().getText();
			String anneePub = view.getAnnePubField().getText();
			String genre = view.getGenreField().getText();
			String quantiteLivre = view.getQuantiteField().getText();

			if (titre.isEmpty() || auteur.isEmpty() || anneePub.isEmpty() || genre.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Tous les champs doivent être remplis.", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int annee = Integer.parseInt(anneePub);
			int quantite = Integer.parseInt(quantiteLivre);
			Livre livre = new Livre(titre, auteur, annee, genre, quantite);
			model.ajouterLivre(livre);

			view.ajouterLivreDansTable(livre.getIsbn(), titre, auteur, annee, genre, quantite);

			reinitialiserForm();

			JOptionPane.showMessageDialog(view, "Livre ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(view, "L'année de publication doit être un nombre valide.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void supprimerLivre() {
		int selectedRow = view.livreTable.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à supprimer.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int isbn = Integer.parseInt(view.livreTable.getValueAt(selectedRow, 0).toString());
			model.supprimerLivre(isbn);

			((DefaultTableModel) view.livreTable.getModel()).removeRow(selectedRow);

			JOptionPane.showMessageDialog(view, "Livre supprimé avec succès.", "Succès",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void modifierLivre() {
		int selectedRow = view.livreTable.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre à modifier.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int isbn = Integer.parseInt(view.livreTable.getValueAt(selectedRow, 0).toString());
			String nvTitre = view.getTitreField().getText();
			String nvAuteur = view.getAuteurField().getText();
			String nvGenre = view.getGenreField().getText();
			String nvAnneePub = view.getAnnePubField().getText();
			String quantiteLivre = view.getQuantiteField().getText();

			if (nvTitre.isEmpty() || nvAuteur.isEmpty() || nvGenre.isEmpty() || nvAnneePub.isEmpty()
					|| quantiteLivre.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Tous les champs doivent être remplis.", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int annee = Integer.parseInt(nvAnneePub);
			int quantite = Integer.parseInt(quantiteLivre);

			model.modifierLivre(isbn, nvTitre, nvAuteur, nvGenre, annee, quantite);

			DefaultTableModel tableModel = (DefaultTableModel) view.livreTable.getModel();
			tableModel.setValueAt(nvTitre, selectedRow, 1);
			tableModel.setValueAt(nvAuteur, selectedRow, 2);
			tableModel.setValueAt(annee, selectedRow, 3);
			tableModel.setValueAt(nvGenre, selectedRow, 4);

			JOptionPane.showMessageDialog(view, "Livre modifié avec succès.", "Succès",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void rechercherLivre() {
		String livreRechercher = view.getSearchField().getText().trim().toLowerCase();

		if (livreRechercher.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Veuillez saisir un critère de recherche.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		DefaultTableModel tableModel = (DefaultTableModel) view.livreTable.getModel();
		tableModel.setRowCount(0);

		for (Livre livre : model.getListe()) {
			if (livre.getTitre().toLowerCase().contains(livreRechercher)
					|| livre.getAuteur().toLowerCase().contains(livreRechercher)
					|| livre.getGenre().toLowerCase().contains(livreRechercher)
					|| String.valueOf(livre.getAnneePublication()).contains(livreRechercher)) {
				tableModel.addRow(new Object[] { livre.getIsbn(), livre.getTitre(), livre.getAuteur(),
						livre.getAnneePublication(), livre.getGenre() });
			}
		}

		if (tableModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(view, "Aucun résultat trouvé.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void afficherLaListeDesLivres() {
		model.lireCSV();

		DefaultTableModel tableModel = (DefaultTableModel) view.livreTable.getModel();
		tableModel.setRowCount(0);

		for (Livre livre : model.getListe()) {
			tableModel.addRow(new Object[] { livre.getIsbn(), livre.getTitre(), livre.getAuteur(),
					livre.getAnneePublication(), livre.getGenre(), livre.getQuantite() });
		}
	}

	public void reinitialiserForm() {
		view.getTitreField().setText("");
		view.getAuteurField().setText("");
		view.getAnnePubField().setText("");
		view.getGenreField().setText("");
		view.getQuantiteField().setText("");
	}
}
