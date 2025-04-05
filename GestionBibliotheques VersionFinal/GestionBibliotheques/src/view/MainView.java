package view;

import javax.swing.*;

public class MainView extends JFrame {
	private JTabbedPane tabbedPane;

	public MainView() {
		// Configuration de la fenêtre principale
		setTitle("Gestion de Bibliothèque");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Création du composant des onglets
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
	}

	public void addTab(String title, JPanel panel) {
		// Vérification et ajout du panneau à l'onglet
		if (panel != null) {
			tabbedPane.addTab(title, panel);
		} else {
			System.err.println("Le panneau pour l'onglet '" + title + "' est null.");
		}
	}
}
