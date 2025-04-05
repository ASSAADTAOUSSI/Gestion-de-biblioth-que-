package model;

import java.time.LocalDate;
import java.util.List;
import exceptions.*;

public interface RetourModelInterface {

	// Ajouter un retour
	void ajouterRetour(Retour retour);

	// Rechercher un retour par ID d'emprunt
	Retour rechercherParID(int idEmprunt) throws RetourNotFoundException;

	// Modifier un retour (par exemple, mettre à jour la date de retour effective)
	void modifierRetour(int idEmprunt, LocalDate nouvelleDateRetourEffective)
			throws RetourNotFoundException, DateRetourInvalideException;

	// Supprimer un retour
	void supprimerRetour(int idEmprunt) throws RetourNotFoundException;

	// Lister tous les retours
	void listerRetours();

	// Sauvegarder la liste des retours dans un fichier CSV
	void sauvegarderCSV();

	// Lire la liste des retours depuis un fichier CSV
	void lireCSV();

	// Calculer la pénalité d'un retour
	double calculerPenalite(Retour retour);
}
