package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;

public class SupprimerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String SUPPR_PIZZA_LIBELLE_MENU = "Supprimer une Pizza";

	public SupprimerPizzaOptionMenu(Scanner scanner, DaoFactory daoFactory) {
		super(SUPPR_PIZZA_LIBELLE_MENU, daoFactory, scanner);
	}

	@Override
	public boolean execute() {

		new ListerPizzaOptionMenu(daoFactory).execute();

		System.out.println("Veuillez choisir la pizza à supprimer. (99 pour abandonner)");
		String codePizza = scanner.next();

		try {
			daoFactory.getPizzaDao().deletePizza(codePizza);
			System.out.println("Pizza supprimée");
		} catch (DaoException e) {
			System.err.println("Echec suppression pizza " + e.getMessage());
		}

		return true;
	}

}
