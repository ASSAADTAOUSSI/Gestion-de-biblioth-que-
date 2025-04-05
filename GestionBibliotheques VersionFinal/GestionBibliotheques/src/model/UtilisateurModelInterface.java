package model;

import exceptions.EmailInvalideException;
import exceptions.MotDePasseInvalideException;
import exceptions.RoleInvalideException;
import exceptions.UtilisateurNotFoundException;

public interface UtilisateurModelInterface {
	void ajouterUtilisateur(Utilisateur utilisateur)
			throws RoleInvalideException, MotDePasseInvalideException, EmailInvalideException;

	Utilisateur rechercherParID(int id) throws UtilisateurNotFoundException, UtilisateurNotFoundException;

	void modifierUtilisateur(int id, String nvemail, String nouveauNom, String nouveauMotDePasse, String nouveauRole)
			throws UtilisateurNotFoundException, RoleInvalideException, MotDePasseInvalideException,
			EmailInvalideException;

	void supprimerUtilisateur(int id) throws UtilisateurNotFoundException;

	void listerUtilisateurs();

	void sauvegarderCSV();

	void lireCSV();
}
