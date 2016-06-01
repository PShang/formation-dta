package fr.pizzeria.dao;

import org.apache.commons.lang3.NotImplementedException;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.dao.commande.ICommandeDao;
import fr.pizzeria.dao.pizza.IPizzaDao;

public class GenericFactoryImpl implements DaoFactory {

	private IPizzaDao pizzaDao;
	private IClientDao clientDao;
	private ICommandeDao commandeDao;

	public GenericFactoryImpl(IPizzaDao pizzaDao, IClientDao clientDao, ICommandeDao commandeDao) {
		super();
		this.pizzaDao = pizzaDao;
		this.clientDao = clientDao;
		this.commandeDao = commandeDao;
	}

	
	@Override
	public IPizzaDao getPizzaDao() {
		check(pizzaDao);
		return pizzaDao;
	}

	private void check(Object implementation) {
		if (implementation == null) {
			throw new NotImplementedException("Dao non implémenté");
		}
	}
	
	@Override
	public IClientDao getClientDao() {
		check(clientDao);
		return clientDao;
	}


	@Override
	public ICommandeDao getCommandeDao() {
		check(commandeDao);
		return commandeDao;
	}

}
