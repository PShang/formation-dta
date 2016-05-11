package fr.pizzeria.dao;

import java.util.List;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	/* Pizza[] findAllPizzas(); */

	List<Pizza> findAllPizzas();

	void savePizza(Pizza newpizza) throws DaoException;

	void updatePizza(String codePizza, Pizza updatepizza) throws DaoException;

	void deletePizza(String codePizza) throws DaoException;

}