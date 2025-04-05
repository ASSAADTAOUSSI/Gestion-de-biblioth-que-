package model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import exceptions.EmpruntNotFoundException;
import exceptions.InvalidLoanException;

/**
 * Classe EmpruntModel. Implémente les fonctionnalités définies dans l'interface
 * EmpruntModelInterface. Gère les opérations de gestion des emprunts, y compris
 * l'ajout, la suppression, la modification et la recherche d'emprunts.
 */
public class EmpruntModel implements EmpruntModelInterface {
	private ArrayList<Emprunt> emprunts = new ArrayList<>();
	private String csvFileName;
	private LivreModel livreModel;
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Constructeur par défaut.
	 */
	public EmpruntModel() {
		super();
	}

	/**
	 * Constructeur avec nom de fichier CSV.
	 * 
	 * @param csvFileName le nom du fichier CSV pour la persistance des données.
	 */
	public EmpruntModel(String csvFileName) {
		super();
		this.csvFileName = csvFileName;
	}

	/**
	 * Constructeur avec fichier CSV et modèle de livres.
	 * 
	 * @param csvFileName le nom du fichier CSV.
	 * @param model       l'instance de LivreModel pour gérer les livres associés
	 *                    aux emprunts.
	 */
	public EmpruntModel(String csvFileName, LivreModel model) {
		super();
		this.csvFileName = csvFileName;
		this.livreModel = model;
	}

	/**
	 * Ajoute un nouvel emprunt dans le système.
	 * 
	 * @param emprunt l'emprunt à ajouter.
	 * @throws EmpruntNotFoundException si le livre n'existe pas ou si l'emprunt
	 *                                  existe déjà.
	 */
	@Override
	public void ajouterEmprunt(Emprunt emprunt) throws EmpruntNotFoundException {
		if (livreModel == null) {
			throw new IllegalStateException("Le modèle de livres (livreModel) n'est pas défini.");
		}

		Livre livre = livreModel.rechercherParTitre(emprunt.getTitreLivre());

		if (livre == null) {
			System.err.println("Livre introuvable avec le titre : " + emprunt.getTitreLivre());
			throw new EmpruntNotFoundException("Le livre n'existe pas ou est indisponible.");
		}

		if (livre.getQuantite() <= 0) {
			System.err.println("Quantité de livre insuffisante : " + livre.getTitre());
			throw new EmpruntNotFoundException("Le livre n'est pas disponible.");
		}

		if (emprunts.contains(emprunt)) {
			throw new EmpruntNotFoundException("L'emprunt existe déjà.");
		}

		emprunts.add(emprunt);
		livre.setQuantite(livre.getQuantite() - 1);
		livreModel.sauvegarderCSV();
		this.sauvegarderCSV();
	}

	/**
	 * Supprime un emprunt existant par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt à supprimer.
	 * @throws EmpruntNotFoundException si l'emprunt n'est pas trouvé.
	 */
	@Override
	public void supprimerEmprunt(int id) throws EmpruntNotFoundException {
		Emprunt emprunt = rechercherEmpruntParID(id);
		if (emprunt == null) {
			throw new EmpruntNotFoundException("L'emprunt avec l'ID " + id + " n'existe pas.");
		}

		Livre livre = livreModel != null ? livreModel.rechercherParTitre(emprunt.getTitreLivre()) : null;
		if (livre != null) {
			livre.setQuantite(livre.getQuantite() + 1);
			livreModel.sauvegarderCSV();
		}

		emprunts.remove(emprunt);
		this.sauvegarderCSV();
	}

	/**
	 * Consulte un emprunt par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt à consulter.
	 * @return l'emprunt correspondant.
	 * @throws EmpruntNotFoundException si l'emprunt n'est pas trouvé.
	 */
	@Override
	public Emprunt consulterEmprunt(int id) throws EmpruntNotFoundException {
		// TODO Auto-generated method stub
		Emprunt emprunt = rechercherEmpruntParID(id);
		if (emprunt != null) {
			return emprunt;
		} else {
			throw new EmpruntNotFoundException("Emprunt avec l'ID " + id + " non trouvé.");
		}
	}

	/**
	 * Recherche un emprunt par son identifiant.
	 * 
	 * @param id l'identifiant de l'emprunt.
	 * @return l'emprunt correspondant.
	 * @throws EmpruntNotFoundException si aucun emprunt n'est trouvé.
	 */
	@Override
	public Emprunt rechercherEmpruntParID(int id) throws EmpruntNotFoundException {
		return emprunts.stream().filter(e -> e.getIdEmprunt() == id).findFirst()
				.orElseThrow(() -> new EmpruntNotFoundException("Emprunt avec l'ID " + id + " non trouvé."));
	}

	/**
	 * Prolonge la durée d'un emprunt existant.
	 * 
	 * @param id        l'identifiant de l'emprunt.
	 * @param joursSupp le nombre de jours à ajouter à la durée actuelle.
	 * @throws EmpruntNotFoundException si l'emprunt n'est pas trouvé.
	 */
	@Override
	public void prolongerEmprunt(int id, int joursSupp) throws EmpruntNotFoundException {
		// TODO Auto-generated method stub
		Emprunt emprunt = rechercherEmpruntParID(id);
		if (emprunt != null) {
			LocalDate newDateRetour = emprunt.getDateRetour().plusDays(joursSupp);
			emprunt.setDateRetour(newDateRetour);
			this.sauvegarderCSV();
		} else {
			throw new EmpruntNotFoundException("Emprunt avec l'ID " + id + " non trouvé.");
		}
	}

	/**
	 * Liste tous les emprunts dans le système.
	 * 
	 * @return une liste d'emprunts.
	 */
	@Override
	public ArrayList<Emprunt> listerEmprunt() {
		// TODO Auto-generated method stub
		return emprunts;
	}

	/**
	 * Charge les données des emprunts depuis un fichier CSV.
	 */
	@Override
	public void lireCSV() {
		// TODO Auto-generated method stub
		emprunts.clear();
		Set<Integer> ids = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
			br.readLine(); // Ignorer la première ligne (en-tête)
			String line;

			while ((line = br.readLine()) != null) {
				String[] words = line.split(";");
				if (words.length < 5)
					continue;

				int id = Integer.parseInt(words[0]);
				int idUser = Integer.parseInt(words[1]);
				String titre = words[2];
				LocalDate dateE = LocalDate.parse(words[3], DATE_FORMATTER);
				LocalDate dateR = LocalDate.parse(words[4], DATE_FORMATTER);

				if (!ids.contains(id)) {
					ids.add(id);
					Emprunt emprunt = new Emprunt(idUser, titre, dateE, dateR);
					emprunts.add(emprunt);
				}
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
		}
	}

	/**
	 * Sauvegarde les données des emprunts dans un fichier CSV.
	 */
	@Override
	public void sauvegarderCSV() {
		// TODO Auto-generated method stub
		supprimerDoublons();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName))) {
			bw.write("Id;IdUtilisateur;TitreLivre;DateEmprunt;DateRetour");
			for (Emprunt emprunt : emprunts) {
				bw.newLine();
				bw.write(emprunt.getIdEmprunt() + ";" + emprunt.getIdUtilisateur() + ";" + emprunt.getTitreLivre() + ";"
						+ emprunt.getDateEmprunt().format(DATE_FORMATTER) + ";"
						+ emprunt.getDateRetour().format(DATE_FORMATTER));
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
		}
	}

	/**
	 * Supprime les doublons dans la liste des emprunts.
	 */
	public void supprimerDoublons() {
		Set<String> empruntUnique = new HashSet<>();
		emprunts.removeIf(emprunt -> !empruntUnique.add(emprunt.getIdUtilisateur() + emprunt.getTitreLivre()
				+ emprunt.getDateEmprunt() + emprunt.getDateRetour()));
	}

	/**
	 * Modifie un emprunt existant.
	 * 
	 * @param id                  l'identifiant de l'emprunt.
	 * @param idUser              le nouvel identifiant de l'utilisateur.
	 * @param nouveauTitre        le nouveau titre du livre.
	 * @param nouvelleDateEmprunt la nouvelle date d'emprunt.
	 * @param nouvelleDateRetour  la nouvelle date de retour.
	 * @throws EmpruntNotFoundException si l'emprunt n'est pas trouvé ou si le
	 *                                  nouveau livre n'est pas disponible.
	 */
	@Override
	public void modifierEmprunt(int id, int idUser, String nouveauTitre, LocalDate nouvelleDateEmprunt,
			LocalDate nouvelleDateRetour) throws EmpruntNotFoundException {
		Emprunt emprunt = rechercherEmpruntParID(id);
		if (emprunt == null) {
			throw new EmpruntNotFoundException("L'emprunt avec l'ID " + id + " n'existe pas.");
		}

		Livre ancienLivre = livreModel.rechercherParTitre(emprunt.getTitreLivre());
		Livre nouveauLivre = livreModel.rechercherParTitre(nouveauTitre);

		if (nouveauLivre == null || nouveauLivre.getQuantite() <= 0) {
			throw new EmpruntNotFoundException("Le nouveau livre '" + nouveauTitre + "' n'est pas disponible.");
		}

		// Rendre l'ancien livre
		if (ancienLivre != null) {
			ancienLivre.setQuantite(ancienLivre.getQuantite() + 1);
		}

		// Décrémenter la quantité du nouveau livre
		nouveauLivre.setQuantite(nouveauLivre.getQuantite() - 1);

		// Mettre à jour l'emprunt
		emprunt.setIdUtilisateur(idUser);
		emprunt.setTitreLivre(nouveauTitre);
		emprunt.setDateEmprunt(nouvelleDateEmprunt);
		emprunt.setDateRetour(nouvelleDateRetour);

		livreModel.sauvegarderCSV();
		this.sauvegarderCSV();
	}

	/**
	 * Recherche des emprunts par titre de livre.
	 * 
	 * @param titre le titre du livre.
	 * @return une liste d'emprunts correspondant au titre.
	 */
	@Override
	public ArrayList<Emprunt> rechercherEmpruntParTitre(String titre) {
		ArrayList<Emprunt> empruntsTrouves = new ArrayList<>();
		for (Emprunt emprunt : emprunts) {
			if (emprunt.getTitreLivre().equalsIgnoreCase(titre)) {
				empruntsTrouves.add(emprunt);
			}
		}
		return empruntsTrouves;
	}

}
