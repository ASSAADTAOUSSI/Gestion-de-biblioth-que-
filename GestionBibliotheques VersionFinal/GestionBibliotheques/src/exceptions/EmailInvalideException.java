package exceptions;

public class EmailInvalideException extends Exception {
	public EmailInvalideException(String email) {
		super("L'email '" + email + "' est invalide. Veuillez saisir un e-mail du type xxx@xxx.xxx");
	}
}
