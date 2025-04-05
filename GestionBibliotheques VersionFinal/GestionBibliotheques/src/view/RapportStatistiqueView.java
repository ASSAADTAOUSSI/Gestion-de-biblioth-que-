package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RapportStatistiqueView extends JFrame {
	private JButton btnGenererRapport;
	private JTextArea textAreaRapport;
	private JPanel mainPanel;

	public RapportStatistiqueView() {
		setTitle("Rapport et Statistiques");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Création du layout principal
		mainPanel = new JPanel(new BorderLayout());

		// Zone de texte pour afficher le rapport
		textAreaRapport = new JTextArea();
		textAreaRapport.setEditable(false); // Rendre la zone non éditable
		JScrollPane scrollPane = new JScrollPane(textAreaRapport);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Bouton pour générer les rapports
		JPanel panel = new JPanel();
		btnGenererRapport = new JButton("Générer Rapport");
		panel.add(btnGenererRapport);

		// Ajout du panneau inférieur contenant le bouton
		mainPanel.add(panel, BorderLayout.SOUTH);

		// Définir le panneau principal comme contenu de la fenêtre
		setContentPane(mainPanel);
	}

	/**
	 * Récupérer le panneau principal pour l'ajouter à l'onglet de la MainView.
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Ajouter un listener au bouton pour la génération de rapports.
	 */
	public void addGenererRapportListener(ActionListener listener) {
		btnGenererRapport.addActionListener(listener);
	}

	/**
	 * Méthode pour afficher le rapport dans la zone de texte.
	 */
	public void setRapportText(String rapport) {
		textAreaRapport.setText(rapport);
	}

	/**
	 * Méthode pour afficher des erreurs.
	 */
	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
