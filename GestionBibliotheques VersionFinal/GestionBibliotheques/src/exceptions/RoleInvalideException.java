package exceptions;

public class RoleInvalideException extends Exception {
	public RoleInvalideException(String role) {
		super("Le rôle '" + role + "' est invalide. Les rôles valides sont : Admin, Bibliothecaire, Membre.");
	}
}
