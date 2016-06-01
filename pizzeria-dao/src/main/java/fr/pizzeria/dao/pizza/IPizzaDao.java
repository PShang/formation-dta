package fr.pizzeria.dao.pizza;


import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;
import org.apache.commons.lang3.NotImplementedException;

public interface IPizzaDao {

	IPizzaDao DEFAULT_IMPLEMENTATION = new PizzaDaoMemoire();
	
	List<Pizza> findAllPizzas() throws DaoException;

    default Pizza findOnePizza(String code) throws DaoException {
        throw new NotImplementedException("findOnePizza non implémenté");
    };

	void savePizza(Pizza newPizza) throws DaoException;
	void updatePizza(String codePizza, Pizza updatePizza) throws DaoException;
	void deletePizza(String codePizza) throws DaoException;
	void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException;

}
