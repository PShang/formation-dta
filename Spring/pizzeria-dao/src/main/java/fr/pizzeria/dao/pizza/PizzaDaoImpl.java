package fr.pizzeria.dao.pizza;

import java.math.BigDecimal;
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

		pizzas.put("PEP", new Pizza(1, "PEP", "Pépéroni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("MAR", new Pizza(2, "MAR", "Margherita", BigDecimal.valueOf(14.00),  CategoriePizza.SANS_VIANDE, "http://placehold.it/150x150"));
		pizzas.put("REI", new Pizza(3, "REI", "La Reine", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("FRO", new Pizza(4, "FRO", "La 4 fromages", BigDecimal.valueOf(12.00),  CategoriePizza.SANS_VIANDE, "http://placehold.it/150x150"));
		pizzas.put("CAN", new Pizza(5, "CAN", "La cannibale", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("SAV", new Pizza(6, "SAV", "La savoyarde", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("ORI", new Pizza(7, "ORI", "L'orientale", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("IND", new Pizza(8, "IND", "L'indienne", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("SAU", new Pizza(9, "SAU", "La Saumonéta", BigDecimal.valueOf(14.00), CategoriePizza.POISSON, "http://placehold.it/150x150"));

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
