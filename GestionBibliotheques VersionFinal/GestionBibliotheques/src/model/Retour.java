package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Retour {
	private int idEmprunt;
	private int idUser; // ID de l'utilisateur
	private String nomUtilisateur; // Nom de l'utilisateur
	private int idLivre; // ID du livre
	private String titreLivre; // Titre du livre
	private LocalDate dateEmprunt;
	private LocalDate dateRetourPrevue;
	private LocalDate dateRetourEffective;

	// Constructeur
	public Retour(int idEmprunt, int idUser, String titreLivre, LocalDate dateEmprunt, LocalDate dateRetourPrevue,
			LocalDate dateRetourEffective) {
		this.idEmprunt = idEmprunt;
		this.titreLivre = titreLivre;
		this.idUser = idUser; // Stocker directement l'ID utilisateur

		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateRetourPrevue;
		this.dateRetourEffective = dateRetourEffective;
	}

	// Getters
	public int getIdEmprunt() {
		return idEmprunt;
	}

	public int getIdUser() {
		return idUser;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public int getIdLivre() {
		return idLivre;
	}

	public String getTitreLivre() {
		return titreLivre;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}

	public LocalDate getDateRetourEffective() {
		return dateRetourEffective;
	}

	public void setDateRetourEffective(LocalDate dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}

	// Calculer les pénalités
	public double calculerPenalite() {
		if (dateRetourEffective != null && dateRetourEffective.isAfter(dateRetourPrevue)) {
			long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
			return joursDeRetard * 50; // 50 dirhams par jour de retard
		}
		return 0; // Aucune pénalité si le livre est retourné dans les temps
	}

	@Override
	public String toString() {
		return "Retour{" + "idEmprunt=" + idEmprunt + ", idUtilisateur=" + idUser +

				", titreLivre='" + titreLivre + '\'' + ", dateEmprunt=" + dateEmprunt + ", dateRetourPrevue="
				+ dateRetourPrevue + ", dateRetourEffective=" + dateRetourEffective + '}';
	}
}
