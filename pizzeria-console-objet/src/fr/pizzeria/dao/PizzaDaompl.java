package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaompl implements IPizzaDao {

	public static class ResultatRecherche {
		boolean pizzaTrouve;
		int indexPizzaTrouve;
	}

	private Map<String, Pizza> pizzas = new HashMap<String, Pizza>();

	public PizzaDaompl() {
		pizzas.put("PEP", new Pizza("PEP", "P�p�roni", 12.50, CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("REI", new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.POISSON));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.SANS_VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return new ArrayList<Pizza>(pizzas.values());
	}



	@Override
	public void savePizza(Pizza newPizza) throws DaoException {

		if (pizzas.containsKey(newPizza.getCode())) {
			throw new SavePizzaException("code pizza d�j� pr�sent");

		}
		pizzas.put(newPizza.getCode(), newPizza);
	}

	
	public Pizza[] findAllPizzas1() {
		Pizza[] resultat = new Pizza[100];
		System.arraycopy(pizzas, 0, resultat, 0, resultat.length);
		return resultat;
	}

	@Override 
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException{
		if(!pizzas.containsKey(codePizza))
		{
			throw new UpdatePizzaException("code pizza non trouv�");
		}
		pizzas.put(codePizza,updatePizza);
		

		// TODO Auto-generated method stub

	}

	// pizzas[index] = null;

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new DeletePizzaException("code pizza non trouv�");
		}
		pizzas.remove(codePizza);

		// TODO Auto-generated method stub

	}

}
