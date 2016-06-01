package fr.pizzeria.ihm.menu.option;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.DesactiverOptionMenu;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Lister Pizzas";

	
	
	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZAS_LIBELLE_MENU, pizzaDao);
	}

	@Override
	public boolean execute() {
		System.out.println("Lister Pizza Menu");
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		Collections.sort(pizzas, new Comparator<Pizza>() {

			@Override
			public int compare(Pizza o1, Pizza o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});
		
		for (Pizza p : pizzas) {
			System.out.println(p);
		}
		
		return true;
	}

}
