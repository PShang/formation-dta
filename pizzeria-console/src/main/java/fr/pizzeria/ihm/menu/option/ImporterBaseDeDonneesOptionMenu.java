package fr.pizzeria.ihm.menu.option;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFichierImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class ImporterBaseDeDonneesOptionMenu extends AbstractOptionMenu{
	private static final String LIBELLE = "(Base de données) Importer les données";
	public ImporterBaseDeDonneesOptionMenu(IPizzaDao pizzaDao)
	{
		super(LIBELLE,pizzaDao);
	}

@Override

public boolean execute()
{
	try
	{
		PizzaDaoFichierImpl daoFichierImpl = new PizzaDaoFichierImpl();
		List<Pizza> listPizzas = daoFichierImpl.findAllPizzas();
		pizzaDao.saveAllPizzas(listPizzas, 3);
	}catch (DaoException e) {
		System.err.println(e.getMessage());}  //recupere le msg de l'exception
	return true;

}
}

