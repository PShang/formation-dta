package fr.pizzeria.ihm.menu.option;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.DaoProducer;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterNouvellePizzaOptionMenuTest {
	
	private AjouterNouvellePizzaOptionMenu optionMenu;
	private IPizzaDao pizzaDao;
	
	@Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Before
	public void setUp() throws Exception {
		Locale.setDefault(Locale.FRENCH);
		Scanner scanner = new Scanner(System.in);
		DaoFactory daoFactory = new DaoProducer().getDaoFactoryMemoire();
		pizzaDao = daoFactory.getPizzaDao(); 
		optionMenu = new AjouterNouvellePizzaOptionMenu(scanner, daoFactory);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		systemInMock.provideLines("NEW", "NewPizza","12,5","0");
		boolean next = optionMenu.execute();
		assertTrue(next);
		List<Pizza> allPizzas = pizzaDao.findAllPizzas();
		Optional<Pizza> pizzaOpt = allPizzas.stream().filter(p->"NEW".equals(p.getCode())).findFirst();
		assertTrue(pizzaOpt.isPresent());
		Pizza pizza = pizzaOpt.get();
		assertEquals("NewPizza", pizza.getNom());
		assertEquals(BigDecimal.valueOf(12.5), pizza.getPrix());
		assertEquals(CategoriePizza.VIANDE, pizza.getCategorie());
		
		String outAttendu = Files.lines(Paths.get("src/test/resources/fr/pizzeria/ihm/menu/option/resultatAjouterNouvellePizza.txt")).collect(Collectors.joining(System.lineSeparator()));
		outAttendu+=System.lineSeparator();
		assertEquals(outAttendu, systemOutRule.getLog());
	}
	
	
	
	
	
	
	
	
	
	
	

}
	
	
	
	
	
	
	
	

}
