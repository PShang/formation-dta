package fr.pizzeria.ihm.menu.option;

import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class AfficherPizzaTarifPlusEleveOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Afficher la pizza au tarif le plus élevé";

	
	
	public AfficherPizzaTarifPlusEleveOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZAS_LIBELLE_MENU, pizzaDao);
	}

	@Override
	public boolean execute() {
		
		try {
			pizzaDao.findAllPizzas().stream()
				.collect(Collectors.maxBy(Comparator.comparing(Pizza::getPrix)))
				.ifPresent(System.out::println);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return true;
	}

}
