package fr.pizzeria.ihm.menu.option;

import java.util.List;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.pizza.PizzaDaoFichierImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class ImporterBaseDeDonneesOptionMenu extends AbstractOptionMenu {

	private static final String LIBELLE = "(Base de données) Importer les données";

	public ImporterBaseDeDonneesOptionMenu(DaoFactory daoFactory) {
		super(LIBELLE, daoFactory, null);
	}

	@Override
	public boolean execute() {
		try {
			PizzaDaoFichierImpl daoFichierImpl = new PizzaDaoFichierImpl();
			List<Pizza> listPizzas = daoFichierImpl.findAllPizzas();
			daoFactory.getPizzaDao().saveAllPizzas(listPizzas, 3);
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		
		return true;
	}

}
