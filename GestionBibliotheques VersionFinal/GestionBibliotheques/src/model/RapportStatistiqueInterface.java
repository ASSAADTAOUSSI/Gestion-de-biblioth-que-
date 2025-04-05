package model;

import java.util.List;
import model.Retour;

public interface RapportStatistiqueInterface {

	// Méthode pour générer le rapport des livres les plus empruntés
	String genererRapportLivresPlusEmpruntes(String empruntCsvFile);

	// Méthode pour générer le rapport des utilisateurs les plus actifs
	String genererRapportUtilisateursPlusActifs(String empruntCsvFile);

	// Méthode pour générer un rapport général sur la bibliothèque
	String genererRapportGeneral(String empruntCsvFile);

}
