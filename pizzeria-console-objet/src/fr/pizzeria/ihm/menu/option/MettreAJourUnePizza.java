package fr.pizzeria.ihm.menu.option;

import java.util.List;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class MettreAJourUnePizza extends AbstractOptionMenu {

	private static final String METTRE_A_JOUR_PIZZA_LIBELLE_MENU = "Mettre à jour une pizza";

	public MettreAJourUnePizza(Scanner scanner, IPizzaDao pizzaDao) {
		super(METTRE_A_JOUR_PIZZA_LIBELLE_MENU, pizzaDao, scanner);

	}

	@Override
	public boolean execute() {
		System.out.println("Mettre à jour une pizza");
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		for(Pizza p:pizzas){
			System.out.println(p.code + "->"+p.nom+"("+p.prix+")");
		}
	
		System.out.println("Veuillez choisir la pizza à modifier(99 pour abandonner)");
		String codePizza = sc.next();

		Pizza p = new Pizza();
		System.out.println("Veuillez saisir le code");
		p.code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		p.nom = sc.next();
		System.out.println("Veuillez saisir le prix");
		p.prix = sc.nextDouble();
		
		try
		{
			pizzaDao.updatePizza(codePizza,p);
		
			System.out.println("Nouvelle pizza créée");
		} catch(DaoException  d)
		{
			System.out.println("Echec création de pizza");
		}
		return true;
	}

}
