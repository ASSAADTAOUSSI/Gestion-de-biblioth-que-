package model;

import java.util.ArrayList;

import exceptions.LivreNotFoundException;

public interface LivreModelInterface {

	public void ajouterLivre(Livre livre) throws LivreNotFoundException;

	public void modifierLivre(int isbn, String nvTitre, String nvAuteur, String nvGenre, int nvAnneePub, int quantite)
			throws LivreNotFoundException;

	public void supprimerLivre(int isbn) throws LivreNotFoundException;

	public void trierListesLivre();

	public ArrayList<Livre> getListe();

	public Livre rechercherParId(int isbn);

	public Livre rechercherParTitre(String titre);

	public void listerLivres();

	public void sauvegarderCSV();

	public void lireCSV();

	public void nettoyerCSV();

	public void supprimerDoublons();
}
