package tests;

import controller.LivreController;
import exceptions.LivreNotFoundException;
import model.*;
import view.LivreView;

public class LivreTest {

	public static void main(String[] args) throws LivreNotFoundException {
		LivreModel model = new LivreModel("Livre.csv");
//		
		model.lireCSV();

//		Livre livre1= new Livre("L'Apprenti Sorcier","J.K. Rowling",1997,"Fantasy");
//		Livre livre2= new Livre("Le Vieil Homme et la Mer","Ernest Hemingway",1952,"Fiction");
//		Livre livre3= new Livre("1984","George Orwell",1949,"Dystopie");
//		Livre livre4= new Livre("La Peste","Albert Camus",1947,"Philosophique");
//		Livre livre5= new Livre("Le Petit Prince","Antoine de Saint-Exupéry",1943,"Fiction");
//		
//		model.ajouterLivre(livre1);
//		model.ajouterLivre(livre2);
//		model.ajouterLivre(livre3);
//		model.ajouterLivre(livre4);
//		model.ajouterLivre(livre5);

		model.sauvegarderCSV();
		LivreView view = new LivreView();
		LivreController controller = new LivreController(model, view);
		view.setVisible(true);
	}
}
