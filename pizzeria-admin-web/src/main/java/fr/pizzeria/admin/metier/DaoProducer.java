package fr.pizzeria.admin.metier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoMemoire;

public class DaoProducer {

	@Produces
	@ApplicationScoped
	private static IPizzaDao getPizzaDao() {
		return new PizzaDaoMemoire();
	}
}
