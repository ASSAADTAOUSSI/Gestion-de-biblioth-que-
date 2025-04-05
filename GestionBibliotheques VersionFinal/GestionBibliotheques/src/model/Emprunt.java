package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * La classe Emprunt représente un emprunt dans le système de gestion de
 * bibliothèque. Chaque emprunt est identifié de manière unique et contient des
 * informations sur l'utilisateur, le livre emprunté, ainsi que les dates
 * d'emprunt et de retour.
 */

public class Emprunt implements Comparable<Emprunt> {
	private int idEmprunt;
	private static int compteur;
	private int idUtilisateur;
	private String titreLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;

	/**
	 * Constructeur par défaut. Génère automatiquement un identifiant unique pour
	 * l'emprunt.
	 */
	public Emprunt() {
		super();
		compteur++;
		idEmprunt = compteur;
	}

	/**
	 * Constructeur avec paramètres.
	 * 
	 * @param idUtilisateur l'identifiant de l'utilisateur.
	 * @param titreLivre    le titre du livre emprunté.
	 * @param dateEmprunt   la date à laquelle le livre a été emprunté.
	 * @param dateRetour    la date prévue pour le retour du livre.
	 */
	public Emprunt(int idUtilisateur, String titreLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.titreLivre = titreLivre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		compteur++;
		idEmprunt = compteur;
	}

	/**
	 * Récupère l'identifiant unique de l'emprunt.
	 * 
	 * @return l'identifiant de l'emprunt.
	 */
	public int getIdEmprunt() {
		return idEmprunt;
	}

	/**
	 * Définit l'identifiant de l'emprunt.
	 * 
	 * @param idEmprunt l'identifiant à définir.
	 */
	public void setIdEmprunt(int idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	/**
	 * Récupère l'identifiant de l'utilisateur associé à l'emprunt.
	 * 
	 * @return l'identifiant de l'utilisateur.
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	/**
	 * Définit l'identifiant de l'utilisateur associé à l'emprunt.
	 * 
	 * @param idUtilisateur l'identifiant de l'utilisateur.
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**
	 * Récupère le titre du livre emprunté.
	 * 
	 * @return le titre du livre.
	 */
	public String getTitreLivre() {
		return titreLivre;
	}

	/**
	 * Définit le titre du livre emprunté.
	 * 
	 * @param titreLivre le titre du livre à définir.
	 */
	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	/**
	 * Récupère la date d'emprunt.
	 * 
	 * @return la date d'emprunt.
	 */
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	/**
	 * Définit la date d'emprunt.
	 * 
	 * @param dateEmprunt la date d'emprunt à définir.
	 */
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	/**
	 * Récupère la date de retour prévue.
	 * 
	 * @return la date de retour prévue.
	 */
	public LocalDate getDateRetour() {
		return dateRetour;
	}

	/**
	 * Définit la date de retour prévue.
	 * 
	 * @param dateRetour la date de retour à définir.
	 */
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	/**
	 * Représentation sous forme de chaîne de caractères de l'emprunt.
	 * 
	 * @return une chaîne contenant les détails de l'emprunt.
	 */
	@Override
	public String toString() {
		return idEmprunt + ";" + idUtilisateur + ";" + titreLivre + ";" + dateEmprunt + ";" + dateRetour;
	}

	/**
	 * Génère un hash code unique pour l'emprunt.
	 * 
	 * @return le hash code basé sur l'identifiant de l'emprunt.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idEmprunt);
	}

	/**
	 * Vérifie si deux emprunts sont égaux en se basant sur leurs identifiants.
	 * 
	 * @param obj l'objet à comparer.
	 * @return true si les emprunts ont le même identifiant, false sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprunt other = (Emprunt) obj;
		return idEmprunt == other.idEmprunt;
	}

	/**
	 * Compare deux emprunts par leurs identifiants.
	 * 
	 * @param emprunt l'emprunt à comparer.
	 * @return une valeur négative, zéro ou positive selon l'ordre des identifiants.
	 */
	@Override
	public int compareTo(Emprunt emprunt) {
		return Integer.compare(this.idEmprunt, emprunt.idEmprunt);
		// return this.nom.compareTo(o.nom);
	}
}
