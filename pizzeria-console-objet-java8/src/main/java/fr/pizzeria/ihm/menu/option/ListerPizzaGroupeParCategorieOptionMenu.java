package fr.pizzeria.ihm.menu.option;

import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class ListerPizzaGroupeParCategorieOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Lister les pizzas groupées par catégorie";

	
	
	public ListerPizzaGroupeParCategorieOptionMenu(DaoFactory dao) {
		super(LISTER_PIZZAS_LIBELLE_MENU, dao, null);
	}

	@Override
	public boolean execute() {
		
		try {
			daoFactory.getPizzaDao().findAllPizzas().stream()
				.collect(Collectors.groupingBy(Pizza::getCategorie))
				.forEach((categorie,listePizzas) -> {
					System.out.println(categorie.getLibelle());
					listePizzas.stream()
						.sorted(Comparator.comparing(Pizza::getCode))
						.forEach(System.out::println);
				});
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		return true;
	}

}
