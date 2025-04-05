package model;

import java.util.List;

public class RapportStatistiques {
	private int totalLivresEmpruntes; // Nombre total de livres empruntés
	private int totalUtilisateursActifs; // Nombre total d'utilisateurs actifs
	private List<String> livresLesPlusEmpruntes; // Liste des livres les plus empruntés
	private List<String> utilisateursAvecPenalites; // Liste des utilisateurs avec les plus de pénalités
	private double totalPenalites; // Total des pénalités accumulées
	private RapportStatistiquesModel model;

	public RapportStatistiques(int totalLivresEmpruntes, int totalUtilisateursActifs,
			List<String> livresLesPlusEmpruntes, List<String> utilisateursAvecPenalites, double totalPenalites) {
		this.totalLivresEmpruntes = totalLivresEmpruntes;
		this.totalUtilisateursActifs = totalUtilisateursActifs;
		this.livresLesPlusEmpruntes = livresLesPlusEmpruntes;
		this.utilisateursAvecPenalites = utilisateursAvecPenalites;
		this.totalPenalites = totalPenalites;
	}

	public int getTotalLivresEmpruntes() {
		return totalLivresEmpruntes;
	}

	public int getTotalUtilisateursActifs() {
		return totalUtilisateursActifs;
	}

	public List<String> getLivresLesPlusEmpruntes() {
		return livresLesPlusEmpruntes;
	}

	public List<String> getUtilisateursAvecPenalites() {
		return utilisateursAvecPenalites;
	}

	public double getTotalPenalites() {
		return totalPenalites;
	}

	// Setters
	public void setTotalLivresEmpruntes(int totalLivresEmpruntes) {
		this.totalLivresEmpruntes = totalLivresEmpruntes;
	}

	public void setTotalUtilisateursActifs(int totalUtilisateursActifs) {
		this.totalUtilisateursActifs = totalUtilisateursActifs;
	}

	public void setLivresLesPlusEmpruntes(List<String> livresLesPlusEmpruntes) {
		this.livresLesPlusEmpruntes = livresLesPlusEmpruntes;
	}

	public void setUtilisateursAvecPenalites(List<String> utilisateursAvecPenalites) {
		this.utilisateursAvecPenalites = utilisateursAvecPenalites;
	}

	public void setTotalPenalites(double totalPenalites) {
		this.totalPenalites = totalPenalites;
	}

	public String genererRapport() {
		String empruntCsvFile = "emprunts.csv"; // Remplace avec le fichier réel
		return genererRapportGeneral(empruntCsvFile);
	}

	@Override
	public String toString() {
		return "RapportStatistiques{" + "totalLivresEmpruntes=" + totalLivresEmpruntes + ", totalUtilisateursActifs="
				+ totalUtilisateursActifs + ", livresLesPlusEmpruntes=" + livresLesPlusEmpruntes
				+ ", utilisateursAvecPenalites=" + utilisateursAvecPenalites + ", totalPenalites=" + totalPenalites
				+ '}';
	}

	public RapportStatistiques() {
		this.model = new RapportStatistiquesModel();
	}

	public String genererRapportLivresPlusEmpruntes(String empruntCsvFile) {
		return model.genererRapportLivresPlusEmpruntes(empruntCsvFile);
	}

	public String genererRapportUtilisateursPlusActifs(String empruntCsvFile) {
		return model.genererRapportUtilisateursPlusActifs(empruntCsvFile);
	}

	public String genererRapportGeneral(String empruntCsvFile) {
		return model.genererRapportGeneral(empruntCsvFile);
	}

}