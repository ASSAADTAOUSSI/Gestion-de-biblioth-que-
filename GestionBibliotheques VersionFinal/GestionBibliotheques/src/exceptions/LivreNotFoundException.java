package exceptions;

public class LivreNotFoundException extends Exception {

	public LivreNotFoundException(int id) {
		super("Le livre n = " + id + "n'existe pas!");
	}

	public LivreNotFoundException(String message) {
		super(message);
	}
}
