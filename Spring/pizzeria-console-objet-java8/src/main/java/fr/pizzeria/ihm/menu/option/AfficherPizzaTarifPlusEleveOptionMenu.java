package fr.pizzeria.ihm.menu.option;

import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class AfficherPizzaTarifPlusEleveOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Afficher la pizza au tarif le plus élevé";

	
	
	public AfficherPizzaTarifPlusEleveOptionMenu(DaoFactory daoFactory) {
		super(LISTER_PIZZAS_LIBELLE_MENU, daoFactory, null);
	}

	@Override
	public boolean execute() {
		
		try {
			daoFactory.getPizzaDao().findAllPizzas().stream()
				.collect(Collectors.maxBy(Comparator.comparing(Pizza::getPrix)))
				.ifPresent(System.out::println);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return true;
	}

}
