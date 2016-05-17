package fr.pizzeria.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;


public class PizzaDaoImpl implements IPizzaDao {
	
	private Map<String, Pizza> pizzas = new HashMap<String, Pizza>();

	public PizzaDaoImpl() {
		pizzas.put("PEP", new Pizza("PEP", "Pépéroni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", BigDecimal.valueOf(14.00),  CategoriePizza.SANS_VIANDE));
		pizzas.put("REI", new Pizza("REI", "La Reine", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", BigDecimal.valueOf(12.00),  CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE));
		pizzas.put("SAU", new Pizza("SAU", "La Saumonéta", BigDecimal.valueOf(14.00), CategoriePizza.POISSON));
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		return new ArrayList<Pizza>(pizzas.values());
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if(pizzas.containsKey(newPizza.getCode())) {
			throw new SavePizzaException("code pizza déjà présent");
		}
		pizzas.put(newPizza.getCode(), newPizza);
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if(!pizzas.containsKey(codePizza)) {
			throw new UpdatePizzaException("code pizza non trouvé");
		}
		pizzas.put(codePizza, updatePizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if(!pizzas.containsKey(codePizza)) {
			throw new DeletePizzaException("code pizza non trouvé");
		}
		pizzas.remove(codePizza);
	}
	public void importerPizzasDepuisFichiers() throws DaoException {
		throw new NotImplementedException("importerPizzasDepuisFichiers non implémenté");
	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		// TODO Auto-generated method stub
		
	}
}


