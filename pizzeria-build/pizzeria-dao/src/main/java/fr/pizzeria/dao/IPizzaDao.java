package fr.pizzeria.dao;


import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas() throws DaoException;
	void savePizza(Pizza newPizza) throws DaoException;
	void updatePizza(String codePizza, Pizza updatePizza) throws DaoException;
	void deletePizza(String codePizza) throws DaoException;

}
