package model;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import exceptions.*;

public class RetourModel implements RetourModelInterface {
	private List<Retour> liste = new ArrayList<>();
	private String csvFileName;

	public RetourModel(String csvFileName) {
		this.csvFileName = csvFileName;

	}

	public void chargerDepuisEmprunts(String empruntCsvFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(empruntCsvFile))) {
			String line;
			br.readLine(); // Ignorer la première ligne (en-têtes)

			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length < 5)
					continue;

				// Lecture des données depuis le CSV
				int idEmprunt = Integer.parseInt(parts[0]);
				int idUser = Integer.parseInt(parts[1]);
				String titreLivre = parts[2];
				LocalDate dateEmprunt = parseDate(parts[3]);
				LocalDate dateRetourPrevue = parseDate(parts[4]);

				// Création de l'objet Retour
				Retour retour = new Retour(idEmprunt, idUser, titreLivre, dateEmprunt, dateRetourPrevue, null // Date de
																												// retour
																												// effective
																												// initialement
																												// vide
				);
				liste.add(retour); // Ajouter l'objet Retour à la liste
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier CSV des emprunts : " + e.getMessage());
		}
	}

	private LocalDate parseDate(String dateStr) {
		if (dateStr == null || dateStr.trim().isEmpty() || dateStr.equals("null")) {
			return null; // Retourne null si la date est vide ou 'null'
		}

		// Essayer d'abord avec le format dd/MM/yyyy
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			return LocalDate.parse(dateStr, formatter1);
		} catch (Exception e) {
			// Si l'exception est levée, essayer avec le format yyyy-MM-dd
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {
				return LocalDate.parse(dateStr, formatter2);
			} catch (Exception ex) {
				// Si les deux formats échouent, afficher un message d'erreur
				throw new IllegalArgumentException("Date au format incorrect: " + dateStr);
			}
		}
	}

	@Override
	public void ajouterRetour(Retour retour) {
		liste.add(retour);
		this.sauvegarderCSV();
	}

	@Override
	public Retour rechercherParID(int idEmprunt) throws RetourNotFoundException {
		return liste.stream().filter(r -> r.getIdEmprunt() == idEmprunt).findFirst()
				.orElseThrow(() -> new RetourNotFoundException(idEmprunt));
	}

	public void modifierRetour(int idEmprunt, LocalDate nouvelleDateRetourEffective) throws RetourNotFoundException {
		Retour retour = rechercherParID(idEmprunt);
		retour.setDateRetourEffective(nouvelleDateRetourEffective);
		this.sauvegarderCSV();
	}

	@Override
	public void supprimerRetour(int idEmprunt) throws RetourNotFoundException {
		Retour retour = rechercherParID(idEmprunt);
		liste.remove(retour);
		this.sauvegarderCSV();
	}

	@Override
	public void listerRetours() {
		liste.forEach(System.out::println);
	}

	@Override
	public void sauvegarderCSV() {
		supprimerDoublons();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName))) {
			bw.write(
					"idEmprunt;idUtilisateur;nomUtilisateur;idLivre;titreLivre;dateEmprunt;dateRetourPrevue;dateRetourEffective");
			for (Retour retour : liste) {
				bw.newLine();
				bw.write(retour.getIdEmprunt() + ";" + retour.getIdUser() + ";" + retour.getTitreLivre() + ";"
						+ retour.getDateEmprunt() + ";" + retour.getDateRetourPrevue() + ";"
						+ retour.getDateRetourEffective());
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
				if (words.length < 6)
					continue;
				int idEmprunt = Integer.parseInt(words[0]);
				int idUser = Integer.parseInt(words[1]);
				String titreLivre = words[2];
				LocalDate dateEmprunt = LocalDate.parse(words[3]);
				LocalDate dateRetourPrevue = LocalDate.parse(words[4]);
				LocalDate dateRetourEffective = (words[5] == null || words[5].trim().isEmpty()
						|| "null".equalsIgnoreCase(words[5])) ? null : LocalDate.parse(words[5]);
				Retour retour = new Retour(idEmprunt, idUser, titreLivre, dateEmprunt, dateRetourPrevue,
						dateRetourEffective);
				liste.add(retour);
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
		}
	}

	public double calculerPenalite(Retour retour) {
		if (retour.getDateRetourEffective() != null
				&& retour.getDateRetourEffective().isAfter(retour.getDateRetourPrevue())) {
			long joursDeRetard = ChronoUnit.DAYS.between(retour.getDateRetourPrevue(), retour.getDateRetourEffective());
			return joursDeRetard * 50;
		}
		return 0;
	}

	public List<Retour> getListe() {
		return new ArrayList<>(liste);
	}

	public void supprimerDoublons() {
		Set<String> retourUnique = new HashSet<>();
		liste.removeIf(retour -> !retourUnique.add(retour.getIdEmprunt() + retour.getIdUser() + retour.getTitreLivre()
				+ retour.getDateEmprunt() + retour.getDateRetourPrevue() + retour.getDateRetourEffective()));
	}
}
