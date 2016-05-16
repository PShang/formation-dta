package fr.pizzeria.dao;

import org.apache.commons.lang3.NotImplementedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas() throws DaoException;
	void savePizza(Pizza newPizza) throws DaoException;
	void updatePizza(String codePizza, Pizza updatePizza) throws DaoException;
	void deletePizza(String codePizza) throws DaoException;
	void saveAllPizza(List<Pizza> listPizzasFichier, int nbGroup) throws DaoException;
	void insertListerPizza(List<List<Pizza>> listPartitionnee, Connection connection) throws DaoException, SQLException;

}

public void importerPizzasDepuisFichiers() throws DaoException{
	throw new NotImplementedException("importerPizzasDepuisFichiers non implémenté");
}

@Override

public void saveAllPizzas(List<Pizza> listPizzas, int nb)throws DaoException{}}