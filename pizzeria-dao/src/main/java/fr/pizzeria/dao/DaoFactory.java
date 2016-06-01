package fr.pizzeria.dao;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.dao.commande.ICommandeDao;
import fr.pizzeria.dao.pizza.IPizzaDao;

public interface DaoFactory {
	
	IPizzaDao getPizzaDao();
	IClientDao getClientDao();
	ICommandeDao getCommandeDao();
	
}
