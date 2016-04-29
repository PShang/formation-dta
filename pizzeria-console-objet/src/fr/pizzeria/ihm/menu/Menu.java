package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.ihm.menu.option.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.option.AjouterUneNouvellePizza;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourUnePizza;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerUnePizza;

public class Menu {
	private static final String MENU_TITRE_LIBELLE = "Application Pizzeria Console";
	private Map<Integer, AbstractOptionMenu> options = new TreeMap<Integer, AbstractOptionMenu>();
	private Scanner sc;

	public Menu(Scanner sc, IPizzaDao pizzaDao) {
		super();
		initialiserOption(sc, pizzaDao);
		this.sc = sc;

	}

	private void initialiserOption(Scanner scanncer, IPizzaDao pizzaDao) {

		options.put(1, new ListerPizzaOptionMenu(pizzaDao));
		options.put(2, new AjouterUneNouvellePizza(sc, pizzaDao));
		options.put(3, new MettreAJourUnePizza(sc, pizzaDao));
		options.put(4, new SupprimerUnePizza(sc, pizzaDao));
		options.put(99, new QuitterOptionMenu());

	}

	public void afficher() throws DeletePizzaException {
		boolean continuer = true;
		while (continuer) {

			System.out.println("****" + MENU_TITRE_LIBELLE + "****");

			/*
			 * Set(Entry<Integer,AbstractOptionMenu> optionMenuEntry :
			 * options.entrySet());
			 */

			for (Entry<Integer, AbstractOptionMenu> optionMenuEntry : options.entrySet())

			{
				System.out.println(optionMenuEntry.getKey() + "." + optionMenuEntry.getValue().getLibelle());
			}

			int saisie = sc.nextInt();

			continuer = options.get(saisie).execute();
		}
	}
}
