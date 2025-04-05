package controller;

import model.*;
import view.*;
import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class RetourController {
	private RetourView vue;
	private RetourModel retourModel;

	public RetourController(RetourView vue, RetourModel retourModel) {
		this.vue = vue;
		this.retourModel = retourModel;
		vue.addModifierDateRetourListener(e -> modifierDateRetourEffective());
		vue.addCalculPenaliteListener(e -> calculerPenalite());
		updateTableData();
	}

	public void updateTableData() {
		List<Retour> retours = retourModel.getListe();
		DefaultTableModel tableModel = (DefaultTableModel) vue.getTable().getModel();

		tableModel.setRowCount(0);

		for (Retour retour : retours) {
			tableModel.addRow(new Object[] { retour.getIdEmprunt(), retour.getTitreLivre(), retour.getIdUser(),
					retour.getDateEmprunt(), retour.getDateRetourPrevue(),
					retour.getDateRetourEffective() != null ? retour.getDateRetourEffective().toString()
							: "Non définie",
					retour.calculerPenalite() + " dirhams" });
		}
	}

	private void modifierDateRetourEffective() {
		try {
			// Vérifier si une ligne est sélectionnée dans la table
			int selectedRow = vue.getSelectedRow();
			if (selectedRow == -1) {
				throw new IllegalArgumentException("Veuillez sélectionner une ligne.");
			}

			// Récupérer la date retour effective saisie par l'utilisateur
			String dateRetourEffectiveStr = vue.getDateRetourEffective();
			if (dateRetourEffectiveStr == null || dateRetourEffectiveStr.trim().isEmpty()) {
				throw new IllegalArgumentException("La date retour effective ne peut pas être vide.");
			}

			// Convertir la date saisie en LocalDate
			LocalDate dateRetourEffective = LocalDate.parse(dateRetourEffectiveStr,
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			// Obtenir l'ID de l'emprunt sélectionné dans la table
			int idEmprunt = (int) vue.getValueAt(selectedRow, 0);
			Retour retour = retourModel.rechercherParID(idEmprunt);

			// Mettre à jour la date retour effective dans le modèle
			retour.setDateRetourEffective(dateRetourEffective);
			retourModel.modifierRetour(idEmprunt, dateRetourEffective);

			// Mettre à jour la table avec la nouvelle date retour effective
			vue.setValueAt(selectedRow, 5, dateRetourEffective.toString()); // Colonne Date Retour Effective

			vue.showMessage("La date retour effective a été modifiée avec succès.");
		} catch (Exception e) {
			vue.showError(e.getMessage());
		}
	}

	private void calculerPenalite() {
		try {
			// Obtenir la ligne sélectionnée dans la vue
			int selectedRow = vue.getSelectedRow();
			if (selectedRow == -1) {
				throw new IllegalArgumentException("Veuillez sélectionner une ligne dans le tableau.");
			}

			// Obtenir la date de retour effective saisie par l'utilisateur
			String dateRetourEffectiveStr = vue.getDateRetourEffective();
			if (dateRetourEffectiveStr == null || dateRetourEffectiveStr.trim().isEmpty()) {
				throw new IllegalArgumentException("Veuillez entrer une date de retour effective.");
			}

			// Convertir la date de retour effective
			LocalDate dateRetourEffective;
			try {
				dateRetourEffective = LocalDate.parse(dateRetourEffectiveStr,
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			} catch (Exception e) {
				throw new IllegalArgumentException("Le format de la date doit être 'yyyy-MM-dd'.");
			}

			// Obtenir l'ID de l'emprunt sélectionné
			int idEmprunt = (int) vue.getValueAt(selectedRow, 0);
			Retour retour = retourModel.rechercherParID(idEmprunt);

			// Comparer la date de retour prévue et la date de retour effective
			LocalDate dateRetourPrevue = retour.getDateRetourPrevue();
			if (dateRetourEffective.isBefore(dateRetourPrevue) || dateRetourEffective.isEqual(dateRetourPrevue)) {
				// Aucun retard
				retour.setDateRetourEffective(dateRetourEffective);
				retourModel.modifierRetour(idEmprunt, dateRetourEffective);
				vue.setValueAt(selectedRow, 6, dateRetourEffective.toString()); // Mettre à jour la date retour
																				// effective
				vue.setValueAt(selectedRow, 6, "0 dirhams"); // Aucune pénalité
				vue.showMessage("Pas de pénalité : Retour effectué à temps ou avant la date prévue.");
			} else {
				// Calcul des pénalités
				long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
				double penalite = joursDeRetard * 50; // Pénalité : 50 dirhams par jour de retard

				retour.setDateRetourEffective(dateRetourEffective);
				retourModel.modifierRetour(idEmprunt, dateRetourEffective);
				vue.setValueAt(selectedRow, 5, dateRetourEffective.toString()); // Mettre à jour la date retour
																				// effective
				vue.setValueAt(selectedRow, 6, penalite + " dirhams");
				vue.showMessage("Retour effectué en retard : " + joursDeRetard + " jours de retard. Pénalité = "
						+ penalite + " dirhams.");
			}
		} catch (Exception e) {
			// Afficher l'erreur dans la vue
			vue.showError(e.getMessage());
		}
	}

}
