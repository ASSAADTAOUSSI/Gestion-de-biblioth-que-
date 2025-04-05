package model;

import java.io.*;
import java.util.*;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceptions.LivreNotFoundException;

public class LivreModel implements LivreModelInterface {

	private ArrayList<Livre> liste=new ArrayList<Livre>();
	private String csvFileName;
	
	
	public LivreModel() {
		super();
	}

	public LivreModel(String csvFileName) {
		super();
		this.csvFileName = csvFileName;
	}

	
	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	@Override
	public void ajouterLivre(Livre livre) throws LivreNotFoundException{
		// TODO Auto-generated method stub
		if(liste.contains(livre)) {
			throw new LivreNotFoundException("Le livre existe deja");
		}
		liste.add(livre);
		this.sauvegarderCSV();
	}

	@Override
	public void modifierLivre(int isbn, String nvTitre, String nvAuteur, String nvGenre, int nvAnneePub,int quantite) throws LivreNotFoundException {
		// TODO Auto-generated method stub
		Livre livre = rechercherParId(isbn);
		if(livre != null) {
			livre.setTitre(nvTitre);
			livre.setAuteur(nvAuteur);
			livre.setGenre(nvGenre);
			livre.setAnneePublication(nvAnneePub);
			livre.setQuantite(quantite);
			this.sauvegarderCSV();
		}
		else {
			throw new LivreNotFoundException(isbn);
		}
	}

	@Override
	public void supprimerLivre(int isbn) throws LivreNotFoundException{
		// TODO Auto-generated method stub
		Livre livre = rechercherParId(isbn);
		if(livre != null) {
			liste.remove(livre);
			this.sauvegarderCSV();
		}else {
			throw new LivreNotFoundException(isbn);
		}
	}

	@Override
	public void trierListesLivre() {
		// TODO Auto-generated method stub
		Collections.sort(liste);
	}

	@Override
	public Livre rechercherParId(int isbn) {
		// TODO Auto-generated method stub
		Optional<Livre> livre=liste.stream().filter(t->t.getIsbn()==isbn).findFirst();
		if(livre.isPresent())
			return livre.get();
		return null;
	}
	
	public Livre rechercherParTitre(String titre) {
		for (Livre livre : liste) {
	        if (livre.getTitre().equalsIgnoreCase(titre)) { // Comparaison insensible à la casse
	            return livre;
	        }
	    }
	    return null; // Si le livre n'est pas trouvé
	}

	@Override
	public void listerLivres() {
		// TODO Auto-generated method stub
		System.out.println(liste);
	}
	
	public ArrayList<Livre> getListe(){
		return liste;
	}

	@Override
	public void sauvegarderCSV() {
		// Supprime les doublons avant de sauvegarder
		supprimerDoublons();
	    try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName));
	        bw.write("Id;Titre;Auteur;Annee Publication;Genre;Quantite");
	        for (Livre livre : liste) {
	            bw.newLine();
	            bw.write(livre.getIsbn() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + 
	                     livre.getAnneePublication() + ";" + livre.getGenre() + ";" + livre.getQuantite());
	        }
	        bw.close();
	    } catch (IOException e) {
	    	System.err.println("Erreur lors de la sauvegarde du fichier CSV : " + e.getMessage());
	    }
	}

	@Override
	public void lireCSV() {
		// TODO Auto-generated method stub
		liste.clear();
		Set<Integer> ids = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFileName));
			br.readLine();
			String line;
			while((line=br.readLine())!=null) {
				String[] words= line.split(";");
				if(words.length<6) continue;
				
				int id = Integer.parseInt(words[0]);
				String titre= words[1].trim();
				String auteur= words[2];
				int anneePub= Integer.parseInt(words[3]);
				String genre= words[4];
				int quantite = Integer.parseInt(words[5]);
				
				if(!ids.contains(id)) {
					ids.add(id);
					Livre livre = new Livre();
					livre.setIsbn(id);
					livre.setTitre(titre);
					livre.setAuteur(auteur);
					livre.setAnneePublication(anneePub);
					livre.setGenre(genre);
					livre.setQuantite(quantite);
					liste.add(livre);
				}
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void nettoyerCSV() {
		Set<String> lignesUniques = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            br.readLine(); // Ignorer l'en-tête
            String line;
            while ((line = br.readLine()) != null) {
                lignesUniques.add(line); // Ajoute uniquement les lignes uniques
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
        
        // Réécriture du fichier avec les données nettoyées
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName))) {
            bw.write("id;nom;motDePasse;role"); // Réécriture de l'en-tête
            for (String ligne : lignesUniques) {
                bw.newLine();
                bw.write(ligne);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier CSV : " + e.getMessage());
        }
	}
	
	public void supprimerDoublons() {
		Set<String> livreUnique = new HashSet<>();
		liste.removeIf(livre -> !livreUnique.add(livre.getTitre()+livre.getAuteur()+livre.getAnneePublication()+livre.getGenre()));
	}
}
