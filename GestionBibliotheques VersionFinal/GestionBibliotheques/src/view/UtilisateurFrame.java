package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class UtilisateurFrame extends JFrame {
	// Composants pour les champs
	private JPanel mainPanel;
	private JTextField idTextField = new JTextField(10);
	private JTextField nomTextField = new JTextField(10);
	private JTextField emailTextField = new JTextField(10);
	private JTextField motDePasseTextField = new JTextField(10);
	private JComboBox<String> roleComboBox = new JComboBox<>(new String[] { "Admin", "Bibliothecaire", "Membre" });

	// Boutons pour les actions
	private JButton ajouterButton = new JButton("Ajouter");
	private JButton modifierButton = new JButton("Modifier");
	private JButton supprimerButton = new JButton("Supprimer");
	private JButton resetButton = new JButton("Réinitialiser");
	private JButton rechercherButton = new JButton("Rechercher");

	// Champ de recherche
	private JTextField rechercheTextField = new JTextField(15);

	// Tableau pour afficher les utilisateurs
	private JTable utilisateursTable;
	private DefaultTableModel tableModel;

	public UtilisateurFrame() {
		// Configuration de la fenêtre
		this.setTitle("EMSI-Gestion de la Bibliothèque");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));

		// Créer les onglets
		JTabbedPane tabbedPane = new JTabbedPane();

		// Ajouter l'onglet Utilisateurs
		JPanel utilisateurPanel = creerOngletUtilisateurs();
		tabbedPane.addTab("Utilisateurs", utilisateurPanel);

		/*
		 * // Ajouter des onglets vides pour d'autres fonctionnalités
		 * tabbedPane.addTab("Livres", creerOngletVide("Livres"));
		 * tabbedPane.addTab("Emprunts", creerOngletVide("Emprunts"));
		 * tabbedPane.addTab("Retours", creerOngletVide("Retours"));
		 */

		// Ajouter les onglets à la fenêtre
		this.add(tabbedPane, BorderLayout.CENTER);

		this.pack(); // Ajuste la taille de la fenêtre
	}

	private JPanel creerOngletUtilisateurs() {
		mainPanel = new JPanel(new BorderLayout());
		// Panneau pour les champs
		JPanel champsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		champsPanel.setBorder(BorderFactory.createTitledBorder("Informations Utilisateur"));
		champsPanel.add(new JLabel("ID :"));
		champsPanel.add(idTextField);
		champsPanel.add(new JLabel("Nom :"));
		champsPanel.add(nomTextField);
		champsPanel.add(new JLabel("email :"));
		champsPanel.add(emailTextField);
		champsPanel.add(new JLabel("Mot de Passe :"));
		champsPanel.add(motDePasseTextField);
		champsPanel.add(new JLabel("Rôle :"));
		champsPanel.add(roleComboBox);

		// Panneau pour les boutons
		JPanel boutonsPanel = new JPanel(new FlowLayout());
		boutonsPanel.add(ajouterButton);
		boutonsPanel.add(modifierButton);
		boutonsPanel.add(supprimerButton);
		boutonsPanel.add(resetButton);

		// Panneau de recherche
		JPanel recherchePanel = new JPanel(new FlowLayout());
		recherchePanel.add(new JLabel("Rechercher :"));
		recherchePanel.add(rechercheTextField);
		recherchePanel.add(rechercherButton);

		// Tableau
		String[] colonnes = { "ID", "Nom", "email", "Mot de Passe", "Rôle" };
		tableModel = new DefaultTableModel(colonnes, 0);
		utilisateursTable = new JTable(tableModel);
		JScrollPane tableauPanel = new JScrollPane(utilisateursTable);
		tableauPanel.setPreferredSize(new Dimension(600, 200)); // Fixer une taille préférée pour le tableau

		// Ajout des sous-panneaux
		mainPanel.add(champsPanel, BorderLayout.NORTH);
		mainPanel.add(recherchePanel, BorderLayout.WEST);
		mainPanel.add(tableauPanel, BorderLayout.CENTER);
		mainPanel.add(boutonsPanel, BorderLayout.PAGE_END);

		return mainPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	private JPanel creerOngletVide(String titre) {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Onglet " + titre + " en construction..."));
		return panel;
	}

	// Méthode pour recharger le tableau
	public void rechargerTable(Object[][] donnees) {
		tableModel.setRowCount(0); // Efface les anciennes données
		for (Object[] ligne : donnees) {
			tableModel.addRow(ligne);
		}
	}

	// Getters pour les composants nécessaires au contrôleur
	public JTextField getIdTextField() {
		return idTextField;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public JTextField getMotDePasseTextField() {
		return motDePasseTextField;
	}

	public JComboBox<String> getRoleComboBox() {
		return roleComboBox;
	}

	public JButton getAjouterButton() {
		return ajouterButton;
	}

	public JButton getModifierButton() {
		return modifierButton;
	}

	public JButton getSupprimerButton() {
		return supprimerButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JButton getRechercherButton() {
		return rechercherButton;
	}

	public JTextField getRechercheTextField() {
		return rechercheTextField;
	}
}
