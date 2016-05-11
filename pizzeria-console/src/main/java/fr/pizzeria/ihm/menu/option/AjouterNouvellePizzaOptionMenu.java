package fr.pizzeria.ihm.menu.option;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 */
public class AjouterNouvellePizzaOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_PIZZA_LIBELLE_MENU = "Ajouter Nouvelle Pizza";

	public AjouterNouvellePizzaOptionMenu(Scanner scanner, IPizzaDao pizzaDao) {
		super(AJOUTER_PIZZA_LIBELLE_MENU, pizzaDao, scanner);
	}

	@Override
	public boolean execute() {

		Pizza newPizza = new Pizza();
		System.out.println("Veuillez saisir le code");
		newPizza.setCode(sc.next());
		System.out.println("Veuillez saisir le nom (sans espace)");
		newPizza.setNom(sc.next());
		System.out.println("Veuillez saisir le prix");
		try {
			newPizza.setPrix(sc.nextDouble());
			
			System.out.println("Veuillez saisir la catégorie");
			
			CategoriePizza[] categoriePizzas = CategoriePizza.values();

			
			Arrays.asList(categoriePizzas).forEach(cat -> System.out.println(cat.ordinal() + " -> " + cat.getLibelle()));
			
			int saisieCategorie = sc.nextInt();
			newPizza.setCategorie(categoriePizzas[saisieCategorie]);
			
			
			
			pizzaDao.savePizza(newPizza);
			System.out.println("Nouvelle pizza créée");

		} catch (InputMismatchException e) {
			System.err.println("Input " + sc.next() + " n'est pas un nombre");
		} catch (DaoException e) {
			System.err.println("Echec création de pizza");
		}
		
		
		
		
		
		return true;
	}

}
