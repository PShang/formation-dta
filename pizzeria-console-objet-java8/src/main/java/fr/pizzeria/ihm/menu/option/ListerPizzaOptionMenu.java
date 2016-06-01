package fr.pizzeria.ihm.menu.option;

import java.util.Comparator;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Lister Pizzas";

	public ListerPizzaOptionMenu(DaoFactory dao) {
		super(LISTER_PIZZAS_LIBELLE_MENU, dao, null); // pas d'utilisation de SC
	}

	@Override
	public boolean execute() {
		System.out.println("Lister Pizza Menu");

		try {
			daoFactory.getPizzaDao().findAllPizzas().stream()
				.sorted(Comparator.comparing(Pizza::getCode))
				.forEach(System.out::println);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
