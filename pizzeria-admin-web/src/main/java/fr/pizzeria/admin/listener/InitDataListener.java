package fr.pizzeria.admin.listener;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@WebListener
public class InitDataListener implements ServletContextListener {
	
	private static final Logger LOG = Logger.getLogger(InitDataListener.class.getName());
	
	@Inject private PizzaService pizzaService;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Map<String, Pizza> pizzas = new HashMap<String, Pizza>();
		pizzas.put("PEP", new Pizza(null, "PEP", "Pépéroni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("MAR", new Pizza(null, "MAR", "Margherita", BigDecimal.valueOf(14.00),  CategoriePizza.SANS_VIANDE, "http://placehold.it/150x150"));
		pizzas.put("REI", new Pizza(null, "REI", "La Reine", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("FRO", new Pizza(null, "FRO", "La 4 fromages", BigDecimal.valueOf(12.00),  CategoriePizza.SANS_VIANDE, "http://placehold.it/150x150"));
		pizzas.put("CAN", new Pizza(null, "CAN", "La cannibale", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("SAV", new Pizza(null, "SAV", "La savoyarde", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("ORI", new Pizza(null, "ORI", "L'orientale", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("IND", new Pizza(null, "IND", "L'indienne", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
		pizzas.put("SAU", new Pizza(null, "SAU", "La Saumonéta", BigDecimal.valueOf(14.00), CategoriePizza.POISSON, "http://placehold.it/150x150"));

		pizzas.forEach((key,value) -> {
			try{
				pizzaService.savePizza(value);
			}catch (DaoException e) {
				LOG.log(Level.SEVERE, "impossible d'insérer une pizza", e);
			}
		});
	}

}
