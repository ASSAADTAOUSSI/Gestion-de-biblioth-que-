package model;

import java.util.Objects;

/*
 * La classe Livre représente un modèle de données dans le cadre du pattern MVC. 
 * Elle est utilisée pour définir les propriétés et les comportements d'un livre 
 * (par exemple : titre, auteur, année de publication, etc.).
 * 
 */
public class Livre implements Comparable<Livre> {

	private String titre;
	private String auteur;
	private int anneePublication;
	private String genre;
	private int isbn; // le id du livre
	private int quantite;
	private static int compteur;

	public Livre() {
		super();
		compteur++;
		isbn = compteur;
	}

	public Livre(String titre, String auteur, int anneePublication, String genre, int quantite) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.anneePublication = anneePublication;
		this.genre = genre;
		this.quantite = quantite;
		compteur++;
		isbn = compteur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return isbn + ";" + titre + ";" + auteur + ";" + anneePublication + ";" + genre + ";" + quantite;
	}

	@Override
	public boolean equals(Object obj) {
		Livre livre = (Livre) obj;
		return livre.isbn == this.isbn;
	}

	@Override
	public int compareTo(Livre livre) {
		return Integer.compare(this.anneePublication, livre.anneePublication);
		// return this.nom.compareTo(o.nom);
	}

}
