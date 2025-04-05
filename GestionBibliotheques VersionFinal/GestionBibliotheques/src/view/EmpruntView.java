package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

/**
 * Classe EmpruntView. Cette classe définit l'interface graphique pour la
 * gestion des emprunts dans le système. Elle permet à l'utilisateur d'effectuer
 * des opérations telles que l'ajout, la modification, la suppression et la
 * recherche d'emprunts.
 */
public class EmpruntView extends JFrame {
	private JPanel mainPanel;
	private JTextField idUserField, titreLivreField, dateEmpruntField, dateRetourField, joursSuppField, rechercherField;
	private JButton ajouterButton, modifierButton, supprimerButton, prolongerButton, rechercherButton, afficherButton,
			resetButton;
	private JTable empruntsTable;
	private DefaultTableModel tableModel;

	/**
	 * Constructeur par défaut. Initialise les composants de l'interface graphique
	 * et configure la fenêtre principale.
	 */
	public EmpruntView() {
		setTitle("Gestion des Emprunts");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialiserComposants();
		ajouterComposants();
		setLocationRelativeTo(null);
	}

	/**
	 * Méthode pour ajouter un emprunt dans la table d'affichage.
	 * 
	 * @param idEmprunt   l'identifiant de l'emprunt.
	 * @param idUser      l'identifiant de l'utilisateur associé à l'emprunt.
	 * @param titre       le titre du livre emprunté.
	 * @param dateEmprunt la date de l'emprunt.
	 * @param dateRetour  la date de retour prévue.
	 */
	public void ajouterEmpruntDansTable(int idEmprunt, int idUser, String titre, LocalDate dateEmprunt,
			LocalDate dateRetour) {
		tableModel.addRow(new Object[] { idEmprunt, idUser, titre, dateEmprunt.toString(), dateRetour.toString() });
	}

	/**
	 * Initialise les composants de l'interface graphique (champs de texte, boutons,
	 * table).
	 */
	public void initialiserComposants() {
		// Champs de texte
		idUserField = new JTextField();
		titreLivreField = new JTextField();
		dateEmpruntField = new JTextField();
		dateRetourField = new JTextField();
		joursSuppField = new JTextField();
		rechercherField = new JTextField();

		// Boutons
		ajouterButton = new JButton("Ajouter");
		modifierButton = new JButton("Modifier");
		supprimerButton = new JButton("Supprimer");
		prolongerButton = new JButton("Prolonger");
		rechercherButton = new JButton("Rechercher");
		afficherButton = new JButton("Afficher");
		resetButton = new JButton("Réinitialiser");

		// Table des emprunts
		String[] columnNames = { "Id Emprunt", "Id Utilisateur", "Titre Livre", "Date Emprunt", "Date Retour" };
		tableModel = new DefaultTableModel(columnNames, 0);
		empruntsTable = new JTable(tableModel);
		empruntsTable.setFillsViewportHeight(true);
	}

	/**
	 * Ajoute les composants dans la fenêtre principale. Configure les panneaux pour
	 * les champs de saisie, les boutons et la table des emprunts.
	 */
	public void ajouterComposants() {
		mainPanel = new JPanel(new BorderLayout());

		// Panel pour les champs de saisie
		JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
		inputPanel.setBorder(BorderFactory.createTitledBorder("Informations de l'Emprunt"));
		inputPanel.add(new JLabel("ID Utilisateur :"));
		inputPanel.add(idUserField);
		inputPanel.add(new JLabel("Titre Livre :"));
		inputPanel.add(titreLivreField);
		inputPanel.add(new JLabel("Date Emprunt (yyyy-MM-dd) :"));
		inputPanel.add(dateEmpruntField);
		inputPanel.add(new JLabel("Date Retour (yyyy-MM-dd) :"));
		inputPanel.add(dateRetourField);
		inputPanel.add(new JLabel("Jours Supplémentaires :"));
		inputPanel.add(joursSuppField);
		inputPanel.add(new JLabel("Rechercher par Titre :"));
		inputPanel.add(rechercherField);

		// Panel pour les boutons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		buttonPanel.add(ajouterButton);
		buttonPanel.add(modifierButton);
		buttonPanel.add(supprimerButton);
		buttonPanel.add(prolongerButton);
		buttonPanel.add(rechercherButton);
		buttonPanel.add(afficherButton);
		buttonPanel.add(resetButton);

		// Panel pour la table
		JScrollPane tableScrollPane = new JScrollPane(empruntsTable);
		tableScrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Emprunts"));

		// Ajout des composants au panel principal
		mainPanel.add(inputPanel, BorderLayout.NORTH);
		mainPanel.add(tableScrollPane, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Définir le panel principal comme contenu de la fenêtre
		setContentPane(mainPanel);
	}

	// Getters pour les composants
	/**
	 * Récupère le panneau principal de l'interface.
	 * 
	 * @return le panneau principal.
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Récupère le champ de texte pour l'ID utilisateur.
	 * 
	 * @return le champ de texte de l'ID utilisateur.
	 */
	public JTextField getIdUserField() {
		return idUserField;
	}

	/**
	 * Récupère le champ de texte pour le titre du livre.
	 * 
	 * @return le champ de texte du titre du livre.
	 */
	public JTextField getTitreLivreField() {
		return titreLivreField;
	}

	/**
	 * Récupère le champ de texte pour la date d'emprunt.
	 * 
	 * @return le champ de texte de la date d'emprunt.
	 */
	public JTextField getDateEmpruntField() {
		return dateEmpruntField;
	}

	/**
	 * Récupère le champ de texte pour la date de retour.
	 * 
	 * @return le champ de texte de la date de retour.
	 */
	public JTextField getDateRetourField() {
		return dateRetourField;
	}

	/**
	 * Récupère le champ de texte pour les jours supplémentaires.
	 * 
	 * @return le champ de texte des jours supplémentaires.
	 */
	public JTextField getJoursSuppField() {
		return joursSuppField;
	}

	/**
	 * Récupère le champ de texte pour la recherche par titre.
	 * 
	 * @return le champ de texte de recherche.
	 */
	public JTextField getRechercherField() {
		return rechercherField;
	}

	/**
	 * Récupère le bouton pour ajouter un emprunt.
	 * 
	 * @return le bouton Ajouter.
	 */
	public JButton getAjouterButton() {
		return ajouterButton;
	}

	/**
	 * Récupère le bouton pour modifier un emprunt.
	 * 
	 * @return le bouton Modifier.
	 */
	public JButton getModifierButton() {
		return modifierButton;
	}

	/**
	 * Récupère le bouton pour supprimer un emprunt.
	 * 
	 * @return le bouton Supprimer.
	 */
	public JButton getSupprimerButton() {
		return supprimerButton;
	}

	/**
	 * Récupère le bouton pour prolonger un emprunt.
	 * 
	 * @return le bouton Prolonger.
	 */
	public JButton getProlongerButton() {
		return prolongerButton;
	}

	/**
	 * Récupère le bouton pour rechercher un emprunt.
	 * 
	 * @return le bouton Rechercher.
	 */
	public JButton getRechercherButton() {
		return rechercherButton;
	}

	/**
	 * Récupère le bouton pour afficher tous les emprunts.
	 * 
	 * @return le bouton Afficher.
	 */
	public JButton getAfficherButton() {
		return afficherButton;
	}

	/**
	 * Récupère le bouton pour réinitialiser les champs de saisie.
	 * 
	 * @return le bouton Réinitialiser.
	 */
	public JButton getResetButton() {
		return resetButton;
	}

	/**
	 * Récupère la table affichant les emprunts.
	 * 
	 * @return la table des emprunts.
	 */
	public JTable getEmpruntsTable() {
		return empruntsTable;
	}

	/**
	 * Récupère le modèle de données de la table.
	 * 
	 * @return le modèle de la table.
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
}
