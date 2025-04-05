package model;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.EmpruntNotFoundException;
import exceptions.InvalidLoanException;

/**
 * Interface EmpruntModelInterface. Définit les méthodes essentielles pour gérer
 * les emprunts dans le système.
 */
public interface EmpruntModelInterface {
	/**
	 * Ajoute un nouvel emprunt dans le système.
	 * 
	 * @param emprunt l'emprunt à ajouter.
	 * @throws EmpruntNotFoundException si une erreur survient lors de l'ajout.
	 */
	void ajouterEmprunt(Emprunt emprunt) throws EmpruntNotFoundException;

	/**
	 * Modifie un emprunt existant.
	 * 
	 * @param id                 l'identifiant de l'emprunt à modifier.
	 * @param idUser             l'identifiant de l'utilisateur associé.
	 * @param nouveauTitre       le nouveau titre du livre.
	 * @param dateEmprunt        la nouvelle date d'emprunt.
	 * @param nouvelleDateRetour la nouvelle date de retour.
	 * @throws EmpruntNotFoundException si l'emprunt avec l'ID spécifié n'est pas
	 *                                  trouvé.
	 */
	void modifierEmprunt(int id, int idUser, String nouveauTitre, LocalDate dateEmprunt, LocalDate nouvelleDateRetour)
			throws EmpruntNotFoundException;

	/**
	 * Supprime un emprunt par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt à supprimer.
	 * @throws EmpruntNotFoundException si l'emprunt avec l'ID spécifié n'est pas
	 *                                  trouvé.
	 */
	void supprimerEmprunt(int id) throws EmpruntNotFoundException;

	/**
	 * Consulte un emprunt par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt.
	 * @return l'emprunt correspondant.
	 * @throws EmpruntNotFoundException si aucun emprunt avec cet ID n'est trouvé.
	 */
	Emprunt consulterEmprunt(int id) throws EmpruntNotFoundException;

	/**
	 * Recherche un emprunt par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt.
	 * @return l'emprunt correspondant.
	 * @throws EmpruntNotFoundException si aucun emprunt avec cet ID n'est trouvé.
	 */
	public Emprunt rechercherEmpruntParID(int id) throws EmpruntNotFoundException;

	/**
	 * Prolonge la durée d'un emprunt.
	 * 
	 * @param id        l'identifiant de l'emprunt.
	 * @param joursSupp le nombre de jours supplémentaires.
	 * @throws EmpruntNotFoundException si l'emprunt avec l'ID spécifié n'est pas
	 *                                  trouvé.
	 */
	void prolongerEmprunt(int id, int joursSupp) throws EmpruntNotFoundException;

	/**
	 * Liste tous les emprunts dans le système.
	 * 
	 * @return une liste contenant tous les emprunts.
	 */
	public ArrayList<Emprunt> listerEmprunt();

	/**
	 * Recherche les emprunts par titre de livre.
	 * 
	 * @param titre le titre du livre à rechercher.
	 * @return une liste d'emprunts correspondant au titre donné.
	 */
	public ArrayList<Emprunt> rechercherEmpruntParTitre(String titre);

	/**
	 * Charge les emprunts depuis un fichier CSV.
	 */
	void lireCSV();

	/**
	 * Sauvegarde les emprunts dans un fichier CSV.
	 */
	void sauvegarderCSV();

	/**
	 * Supprime les doublons dans la liste des emprunts.
	 */
	public void supprimerDoublons();
}
