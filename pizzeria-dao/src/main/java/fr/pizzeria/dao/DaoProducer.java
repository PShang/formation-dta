package fr.pizzeria.dao;

import javax.persistence.EntityManagerFactory;

import fr.pizzeria.dao.client.ClientDaoJPa;
import fr.pizzeria.dao.commande.CommandeDaoJpa;
import fr.pizzeria.dao.pizza.PizzaDaoFichierImpl;
import fr.pizzeria.dao.pizza.PizzaDaoMemoire;
import fr.pizzeria.dao.pizza.PizzaDaoJPA;
import fr.pizzeria.dao.pizza.PizzaDaoJdbc;
import fr.pizzeria.exception.DaoException;

public class DaoProducer {

	public DaoFactory getDaoFactoryJpa(EntityManagerFactory emf) throws DaoException {
		return new GenericFactoryImpl(new PizzaDaoJPA(emf), new ClientDaoJPa(emf), new CommandeDaoJpa(emf));
	}
	
	public DaoFactory getDaoFactoryMemoire() {
		return new GenericFactoryImpl(new PizzaDaoMemoire(), null, null);
	}
	
	public DaoFactory getDaoFactoryFichier() {
		return new GenericFactoryImpl(new PizzaDaoFichierImpl(), null, null);
	}
	
	public DaoFactory getDaoFactoryJdbc(String driver, String url, String user, String pass) throws DaoException {
		return new GenericFactoryImpl(new PizzaDaoJdbc(driver, url, user, pass), null, null);
	}
}
