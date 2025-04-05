package model;

import java.io.*;
import java.util.*;

import exceptions.EmailInvalideException;
import exceptions.MotDePasseInvalideException;
import exceptions.RoleInvalideException;
import exceptions.UtilisateurNotFoundException;

public class UtilisateurModel implements UtilisateurModelInterface {
	private List<Utilisateur> liste = new ArrayList<>();
	private String csvFileName;

	public UtilisateurModel(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) throws MotDePasseInvalideException, RoleInvalideException {
		List<String> rolesValides = Arrays.asList("Admin", "Bibliothecaire", "Membre");
		if (!rolesValides.contains(utilisateur.getRole())) {
			throw new RoleInvalideException(utilisateur.getRole());
		}
		if (utilisateur.getMotDePasse().length() < 6) {
			throw new MotDePasseInvalideException("Le mot de passe doit contenir au moins 6 caractères.");
		}
		if (liste.stream().anyMatch(u -> u.getId() == utilisateur.getId() || u.getNom().equals(utilisateur.getNom()))) {
			throw new IllegalArgumentException(
					"Un utilisateur avec cet ID ou ce nom existe déjà : " + utilisateur.getNom());
		}

		liste.add(utilisateur);
		this.sauvegarderCSV();
	}

	@Override
	public Utilisateur rechercherParID(int id) throws UtilisateurNotFoundException {
		return liste.stream().filter(u -> u.getId() == id).findFirst()
				.orElseThrow(() -> new UtilisateurNotFoundException(id));
	}

	public void modifierUtilisateur(int id, String nouveauNom, String nvemail, String nouveauMotDePasse,
			String nouveauRole) throws UtilisateurNotFoundException, RoleInvalideException, MotDePasseInvalideException,
			EmailInvalideException {
		Utilisateur utilisateur = rechercherParID(id);
		List<String> rolesValides = Arrays.asList("Admin", "Bibliothecaire", "Membre");
		if (!rolesValides.contains(nouveauRole)) {
			throw new RoleInvalideException(nouveauRole);
		}
		if (nouveauMotDePasse.length() < 6) {
			throw new MotDePasseInvalideException("Le mot de passe doit contenir au moins 6 caractères.");
		}

		utilisateur.setNom(nouveauNom);
		utilisateur.setEmail(nvemail);
		utilisateur.setMotDePasse(nouveauMotDePasse);
		utilisateur.setRole(nouveauRole);
		this.sauvegarderCSV();
	}

	@Override
	public void supprimerUtilisateur(int id) throws UtilisateurNotFoundException {
		Utilisateur utilisateur = rechercherParID(id);
		liste.remove(utilisateur);
		this.sauvegarderCSV();
	}

	@Override
	public void listerUtilisateurs() {
		liste.forEach(System.out::println);
	}

	@Override
	public void sauvegarderCSV() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName))) {
			bw.write("id;nom;email;motDePasse;role");
			for (Utilisateur utilisateur : liste) {
				bw.newLine();
				bw.write(utilisateur.getId() + ";" + utilisateur.getNom() + ";" + utilisateur.getEmail() + ";"
						+ utilisateur.getMotDePasse() + ";" + utilisateur.getRole());
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la sauvegarde du fichier CSV : " + e.getMessage());
		}
	}

	@Override
	public void lireCSV() {
		liste.clear();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(";");
				if (words.length < 5)
					continue;

				int id = Integer.parseInt(words[0]);
				String nom = words[1];
				String email = words[2];
				String motDePasse = words[3];
				String role = words[4];

				Utilisateur utilisateur = new Utilisateur(nom, email, motDePasse, role);
				utilisateur.setId(id);
				liste.add(utilisateur);
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
		}
	}

	public List<Utilisateur> getListe() {
		return new ArrayList<>(liste);
	}
}
