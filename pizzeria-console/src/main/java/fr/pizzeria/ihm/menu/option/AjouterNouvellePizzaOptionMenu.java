package fr.pizzeria.ihm.menu.option;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 */
public class AjouterNouvellePizzaOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_PIZZA_LIBELLE_MENU = "Ajouter Nouvelle Pizza";

	public AjouterNouvellePizzaOptionMenu(Scanner scanner, DaoFactory daoFactory) {
		super(AJOUTER_PIZZA_LIBELLE_MENU, daoFactory, scanner);
	}

	@Override
	public boolean execute() {

		Pizza newPizza = new Pizza();
		System.out.println("Veuillez saisir le code");
		newPizza.setCode(scanner.next());
		System.out.println("Veuillez saisir le nom (sans espace)");
		newPizza.setNom(scanner.next());
		System.out.println("Veuillez saisir le prix");
		try {
			newPizza.setPrix(BigDecimal.valueOf(scanner.nextDouble()));
			
			System.out.println("Veuillez saisir la catégorie");
			
			CategoriePizza[] categoriePizzas = CategoriePizza.values();

			
			Arrays.asList(categoriePizzas).forEach(cat -> System.out.println(cat.ordinal() + " -> " + cat.getLibelle()));
			
			int saisieCategorie = scanner.nextInt();
			newPizza.setCategorie(categoriePizzas[saisieCategorie]);
			
			
			
			daoFactory.getPizzaDao().savePizza(newPizza);
			System.out.println("Nouvelle pizza créée");

		} catch (InputMismatchException e) {
			System.err.println("Input " + scanner.next() + " n'est pas un nombre");
		} catch (DaoException e) {
			System.err.println("Echec création de pizza. La cause " + e.getMessage());
		}
		
		
		
		
		
		return true;
	}

}