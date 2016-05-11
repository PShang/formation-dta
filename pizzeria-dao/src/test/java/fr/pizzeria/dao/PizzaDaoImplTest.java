package fr.pizzeria.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImplTest {

	private PizzaDaoImpl pizzaDaoImpl;
	private List<Pizza> listePizzasInitiales;

	@Before
	public void setUp() throws Exception {
		pizzaDaoImpl = new PizzaDaoImpl();
		listePizzasInitiales = new ArrayList<Pizza>();
		listePizzasInitiales.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		listePizzasInitiales.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		listePizzasInitiales.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		listePizzasInitiales.add(new Pizza("SAU", "La Saumonéta", 14.00, CategoriePizza.POISSON));
		listePizzasInitiales.sort(Comparator.comparing(Pizza::getCode));
	}

	@Test
	public void testFindAllPizzas() {
		List<Pizza> resultat = pizzaDaoImpl.findAllPizzas();
		resultat.sort(Comparator.comparing(Pizza::getCode));
		Pizza[] tabPizzasInitiales = listePizzasInitiales.<Pizza> toArray(new Pizza[0]);
		Pizza[] tabResultatObtenu = resultat.<Pizza> toArray(new Pizza[0]);
		assertArrayEquals(tabPizzasInitiales, tabResultatObtenu);

	}

	@Test
	public void testSavePizzaCodeNonExistant() throws DaoException {
		Pizza newPizza = new Pizza("CODE_INEXISTANT", "Nouveau nom", 15, CategoriePizza.VIANDE);
		pizzaDaoImpl.savePizza(newPizza);
		List<Pizza> listPizzas = pizzaDaoImpl.findAllPizzas();
		assertTrue(listPizzas.contains(newPizza));
	}

	@Test(expected = SavePizzaException.class)
	public void testSavePizzaCodeExistant() throws DaoException {
		Pizza newPizza = new Pizza("PEP", "Nouveau nom", 15, CategoriePizza.VIANDE);
		pizzaDaoImpl.savePizza(newPizza);
	}

	@Test
	public void testUpdatePizzaCodeExistant() throws DaoException {
		Pizza updatePizza = new Pizza("PEP", "PEP2", 15, CategoriePizza.VIANDE);
		pizzaDaoImpl.updatePizza("PEP", updatePizza);
		List<Pizza> listPizzas = pizzaDaoImpl.findAllPizzas();
		Optional<Pizza> pizzaOpt = listPizzas.stream().filter(p -> "PEP".equals(p.getCode())).findFirst();
		assertTrue(pizzaOpt.isPresent());
		Pizza pizzaTrouve = pizzaOpt.get();
		assertEquals("PEP", pizzaTrouve.getCode());
		assertEquals("PEP2", pizzaTrouve.getNom());
		assertTrue(pizzaTrouve.getPrix() == 15.0);
		assertEquals(CategoriePizza.VIANDE, pizzaTrouve.getCategorie());
	}

	@Test
	public void testDeletePizza() throws DaoException {
		List<Pizza> listPizzas = pizzaDaoImpl.findAllPizzas();
		assertEquals(9, listPizzas.size());
		pizzaDaoImpl.deletePizza("PEP");
		listPizzas = pizzaDaoImpl.findAllPizzas();
		assertEquals(8, listPizzas.size());
		Optional<Pizza> pizzaOpt = listPizzas.stream().filter(p -> "PEP".equals(p.getCode())).findFirst();
		assertFalse(pizzaOpt.isPresent());
	}
	
	@Test(expected = DeletePizzaException.class)
	public void testDeletePizzaCodeInexistant() throws DaoException {
		pizzaDaoImpl.deletePizza("PEP1");
	}

}
