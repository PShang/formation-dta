package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.option.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.option.AfficherPizzaTarifPlusEleveOptionMenu;
import fr.pizzeria.ihm.menu.option.AjouterNouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzaGroupeParCategorieOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

public class Menu {

	private static final String MENU_TITRE_LIBELLE = "Application Pizzeria Console";
	private Map<Integer, AbstractOptionMenu> options = new TreeMap<Integer, AbstractOptionMenu>();
	private Scanner sc;

	public Menu(Scanner sc, IPizzaDao pizzaDao) {
		super();
		initialiserOptions(sc, pizzaDao);
		this.sc = sc;
	}

	private void initialiserOptions(Scanner scanner, IPizzaDao pizzaDao) {
		options.put(1, new ListerPizzaOptionMenu(pizzaDao));
		options.put(2, new AjouterNouvellePizzaOptionMenu(scanner, pizzaDao));
		options.put(3, new MettreAJourPizzaOptionMenu(scanner, pizzaDao));
		options.put(4, new SupprimerPizzaOptionMenu(scanner, pizzaDao));
		options.put(5, new ListerPizzaGroupeParCategorieOptionMenu(pizzaDao));
		options.put(6, new AfficherPizzaTarifPlusEleveOptionMenu(pizzaDao));
		options.put(99, new QuitterOptionMenu());
	}

	public void afficher() {
		boolean continuer = true;
		while (continuer) {
			System.out.println("**** " + MENU_TITRE_LIBELLE + " ****");
			
			options.forEach((cle,valeur) -> System.out.println(cle + ". " + valeur.getLibelle()));
						
			int saisie = sc.nextInt();
			continuer = options.get(saisie).execute();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
