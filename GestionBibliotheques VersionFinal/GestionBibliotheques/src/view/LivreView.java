package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LivreView extends JFrame {
	private JPanel mainPanel;
	private JLabel titreLabel, auteurLabel, anneePubLabel, genreLabel, quantiteLabel;
	private JTextField titreField, auteurField, annePubField, genreField, quantiteField, searchTextField;
	private JButton addButton, modifyButton, deleteButton, resetButton, searchButton;
	private DefaultTableModel tableModel;
	public JTable livreTable;

	public LivreView() {
		this.setTitle("Gestion des Livres");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialiserComposantes();
		this.addComposantes();
		this.setLocationRelativeTo(null);
	}

	// Méthode pour ajouter un livre dans la table
	public void ajouterLivreDansTable(int isbn, String titre, String auteur, int annee, String genre, int quantite) {
		tableModel.addRow(new Object[] { isbn, titre, auteur, annee, genre, quantite });
	}

	public void initialiserComposantes() {
		mainPanel = new JPanel(new BorderLayout());

		titreLabel = new JLabel("Titre:");
		auteurLabel = new JLabel("Auteur:");
		anneePubLabel = new JLabel("Année de Publication:");
		genreLabel = new JLabel("Genre:");
		quantiteLabel = new JLabel("Quantité:");

		titreField = new JTextField(15);
		auteurField = new JTextField(15);
		annePubField = new JTextField(15);
		genreField = new JTextField(15);
		quantiteField = new JTextField(15);

		addButton = new JButton("Ajouter");
		modifyButton = new JButton("Modifier");
		deleteButton = new JButton("Supprimer");
		resetButton = new JButton("Réinitialiser");
		searchButton = new JButton("Rechercher");

		searchTextField = new JTextField(15);

		// Initialisation de la JTable avec un modèle par défaut
		tableModel = new DefaultTableModel(new String[] { "Isbn", "Titre", "Auteur", "Année", "Genre", "Quantité" }, 0);
		livreTable = new JTable(tableModel);
	}

	public void addComposantes() {
		// Panneau de formulaire
		JPanel panelForm = new JPanel(new GridBagLayout());
		panelForm.setBorder(BorderFactory.createTitledBorder("Informations du Livre"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Champs de formulaire
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelForm.add(titreLabel, gbc);
		gbc.gridx = 1;
		panelForm.add(titreField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelForm.add(auteurLabel, gbc);
		gbc.gridx = 1;
		panelForm.add(auteurField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelForm.add(anneePubLabel, gbc);
		gbc.gridx = 1;
		panelForm.add(annePubField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelForm.add(genreLabel, gbc);
		gbc.gridx = 1;
		panelForm.add(genreField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelForm.add(quantiteLabel, gbc);
		gbc.gridx = 1;
		panelForm.add(quantiteField, gbc);

		// Boutons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(resetButton);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		panelForm.add(buttonPanel, gbc);

		// Composants de recherche
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		panelForm.add(new JLabel("Rechercher:"), gbc);
		gbc.gridx = 1;
		panelForm.add(searchTextField, gbc);
		gbc.gridx = 2;
		panelForm.add(searchButton, gbc);

		// Panneau de la table
		JScrollPane scrollPane = new JScrollPane(livreTable);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Livres"));

		// Panneau principal
		mainPanel.add(panelForm, BorderLayout.NORTH); // Formulaire au Nord
		mainPanel.add(scrollPane, BorderLayout.CENTER); // Table au Centre

		// Ajouter le panel principal à la fenêtre
		setContentPane(mainPanel);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JTextField getTitreField() {
		return titreField;
	}

	public JTextField getAuteurField() {
		return auteurField;
	}

	public JTextField getAnnePubField() {
		return annePubField;
	}

	public JTextField getGenreField() {
		return genreField;
	}

	public JTextField getQuantiteField() {
		return quantiteField;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getModifyButton() {
		return modifyButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JTextField getSearchField() {
		return searchTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}
}
