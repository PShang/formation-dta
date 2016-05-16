package fr.pizzeria.ihm.menu.option;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class MettreAJourPizzaOptionMenu extends AbstractOptionMenu {

	private static final String MAJ_PIZZA_LIBELLE_MENU = "Mettre à jour une Pizza";

	public MettreAJourPizzaOptionMenu(Scanner scanner, IPizzaDao pizzaDao) {
		super(MAJ_PIZZA_LIBELLE_MENU, pizzaDao, scanner);
	}

	@Override
	public boolean execute() {

		new ListerPizzaOptionMenu(pizzaDao).execute();
		
		System.out.println("Veuillez choisir la pizza à modifier. (99 pour abandonner)");
		String codePizza = sc.next();
		
		Pizza updatePizza = new Pizza();
		System.out.println("Veuillez saisir le code");
		updatePizza.setCode(sc.next());
		System.out.println("Veuillez saisir le nom (sans espace)");
		updatePizza.setNom(sc.next());
		System.out.println("Veuillez saisir le prix");
		updatePizza.setPrix(sc.nextDouble());
		CategoriePizza[] categoriePizzas = CategoriePizza.values();
		Arrays.asList(categoriePizzas).forEach(cat -> System.out.println(cat.ordinal() + " -> " + cat.getLibelle()));
		
		
		int saisieCategorie = sc.nextInt();
		updatePizza.setCategorie(categoriePizzas[saisieCategorie]);
		
		try {
			pizzaDao.updatePizza(codePizza, updatePizza);
			System.out.println("Pizza mise à jour");
		} catch (SavePizzaException e) {
			System.err.println("Echec mise à jour pizza" + e.getMessage());
		} catch (UpdatePizzaException e) {
			System.err.println("Echec mise à jour pizza" + e.getMessage());
		} catch (DaoException e) {
			e.printStackTrace();
			System.err.println("Echec mise à jour pizza" + e.getMessage());
		}


			
	

		return true;
	}

}
