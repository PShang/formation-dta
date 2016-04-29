package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;

public class SupprimerUnePizza extends AbstractOptionMenu {

	private static final String SUPPRIMER_UNE_PIZZA = "Supprimer une pizza";

	public SupprimerUnePizza(Scanner scanner, IPizzaDao pizzaDao) {
		super(SUPPRIMER_UNE_PIZZA, pizzaDao, scanner);

	}

	@Override
	public boolean execute() {

		new ListerPizzaOptionMenu(pizzaDao).execute();

		System.out.println("Veuillez choisir la pizza à supprimer. (99 pour abandonner)");
		String codePizza = sc.next();

		try {
			pizzaDao.deletePizza(codePizza);
			System.out.println("Pizza supprimée");
		}

		catch (DaoException d) {
			System.err.println("Echec suppression pizza");

		}
		return true;
	}
}

/*
 * private static void afficherListePizzas(Pizza[] pizzas) { for (int i = 0; i <
 * pizzas.length; i++) { if (pizzas[i] != null) {
 * System.out.println(pizzas[i].code + " -> " + pizzas[i].nom + " (" +
 * pizzas[i].prix + ")");
 */
