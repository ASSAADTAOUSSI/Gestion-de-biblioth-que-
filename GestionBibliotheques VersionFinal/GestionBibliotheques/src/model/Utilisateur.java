package model;

public class Utilisateur implements Comparable<Utilisateur> {
	public Utilisateur(int id, String nom, String email, String motDePasse, String role) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
	}

	private int id;
	private static int compteur = 1;
	private String nom;
	private String email;
	private String motDePasse;
	private String role; // Admin, Bibliothecaire, Membre

	public Utilisateur(String nom, String email, String motDePasse, String role) {
		this.id = compteur++;
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return id + ";" + nom + ";" + email + ";" + motDePasse + ";" + role;
	}

	@Override
	public int compareTo(Utilisateur o) {
		return this.nom.compareTo(o.nom);
	}
}