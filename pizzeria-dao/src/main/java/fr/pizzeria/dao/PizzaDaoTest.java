package fr.pizzeria.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class PizzaDaoTest {

	private static final int NB_INITIAL_PIZZA = 6;

	protected IPizzaDao pizzaDao;

	@Test
	public void testfindAllPizzas() throws DaoException {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA, pizzas.size());
	}

	@Test
	public void testSavePizza() throws DaoException {
		Pizza pizza = new Pizza(null, "PEP X", "Pépéroni X", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE,
				"http://placehold.it/150x150");
		pizzaDao.savePizza(pizza);
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA + 1, pizzas.size());
		Pizza pizzaResult = pizzaDao.findOnePizza("PEP X");
		assertEquals("Pépéroni X", pizzaResult.getNom());
	}

	@Test
	public void testUpdatePizza() throws DaoException {
		Pizza onePizza = pizzaDao.findOnePizza("PEP");
		onePizza.setNom("Pépéroni 2");
		pizzaDao.updatePizza("PEP", onePizza);

		Pizza pizzaResult = pizzaDao.findOnePizza("PEP");
		assertEquals("Pépéroni 2", pizzaResult.getNom());
	}

	@Test
	public void testDeletePizza() throws DaoException {
		pizzaDao.deletePizza("PEP");
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA - 1, pizzas.size());
	}

	@Test
	public void testSaveAllPizza() throws DaoException {
		List<Pizza> pizzas = getListePizzas();
		pizzaDao.saveAllPizzas(pizzas, 3);

		List<Pizza> pizzasBdd = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA + pizzas.size(), pizzasBdd.size());
	}

	@Test
	public void testSaveAllPizzaRollback() throws DaoException {
		List<Pizza> pizzas = getListePizzasWithErrors();
		try {
			pizzaDao.saveAllPizzas(pizzas, 7);
			fail("une exception aurait dû être lancée");
		} catch (DataAccessException | DaoException e) {

			assertNotNull(pizzaDao.findOnePizza("PEP 1"));
			assertNotNull(pizzaDao.findOnePizza("MAR 1"));
			assertNotNull(pizzaDao.findOnePizza("REI 1"));
			assertNotNull(pizzaDao.findOnePizza("FRO 1"));
			assertNotNull(pizzaDao.findOnePizza("CAN 1"));
			assertNotNull(pizzaDao.findOnePizza("SAV 1"));
			assertNotNull(pizzaDao.findOnePizza("ORI 1"));

			// IND 1 et SAU 1 ne doivent pas être insérés
			assertNull(pizzaDao.findOnePizza("IND 1"));
			assertNull(pizzaDao.findOnePizza("SAU 1"));
		}

	}

	private List<Pizza> getListePizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza(null, "PEP 1", "Pépéroni 1", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "MAR 1", "Margherita 1", BigDecimal.valueOf(14.00), CategoriePizza.SANS_VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "REI 1", "La Reine 1", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "FRO 1", "La 4 fromages 1", BigDecimal.valueOf(12.00), CategoriePizza.SANS_VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "CAN 1", "La cannibale 1", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "SAV 1", "La savoyarde 1", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "ORI 1", "L'orientale 1", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "IND 1", "L'indienne 1", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));
		pizzas.add(new Pizza(null, "SAU 1", "La Saumonéta 1", BigDecimal.valueOf(14.00), CategoriePizza.POISSON,
				"http://placehold.it/150x150"));

		return pizzas;
	}

	private List<Pizza> getListePizzasWithErrors() {
		List<Pizza> pizzas = getListePizzas();
		pizzas.add(new Pizza(null, "IND 1", "L'indienne 1", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE,
				"http://placehold.it/150x150"));

		return pizzas;
	}

}