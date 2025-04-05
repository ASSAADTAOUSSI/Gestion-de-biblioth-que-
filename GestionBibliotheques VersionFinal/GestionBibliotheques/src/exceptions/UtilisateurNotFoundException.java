package exceptions;

public class UtilisateurNotFoundException extends Exception {
	private int utilisateurId;

	public UtilisateurNotFoundException(int utilisateurId) {
		super("Utilisateur avec l'ID " + utilisateurId + " introuvable.");
		this.utilisateurId = utilisateurId;
	}

	public UtilisateurNotFoundException(String message) {
		super(message);
	}

	public int getUtilisateurId() {
		return utilisateurId;
	}
}
