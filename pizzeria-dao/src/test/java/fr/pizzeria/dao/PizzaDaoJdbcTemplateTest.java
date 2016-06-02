package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.pizzeria.dao.pizza.IPizzaDao;

public class PizzaDaoJdbcTemplateTest extends PizzaDaoTest {

	@Autowired
	public void setPIzzaDao(@Qualifier("pizzaDaoJdbcTemplate") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
