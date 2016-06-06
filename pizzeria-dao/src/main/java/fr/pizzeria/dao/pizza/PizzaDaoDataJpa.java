package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.logging.Logger;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;
//Créer une nouvelle implémentation de l’interface « IPizzaDao » qui injecte une instance de IPizzaRepository.
public class PizzaDaoDataJpa implements IPizzaDao {
	private static final Logger LOG = Logger.getLogger(PizzaDaoJdbcTemplate.class.toString());

	public PizzaDaoDataJpa PizzaDaoDataJpa;
	
	@Override
	public List<Pizza> findAllPizzas() throws DaoException {

		return null;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {

		
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {

	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		
	}

}
