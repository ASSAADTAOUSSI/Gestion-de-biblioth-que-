package exceptions;

public class RetourNotFoundException extends Exception {
	public RetourNotFoundException(int idEmprunt) {
		super("Retour non trouvé pour l'ID d'emprunt : " + idEmprunt);
	}
}
