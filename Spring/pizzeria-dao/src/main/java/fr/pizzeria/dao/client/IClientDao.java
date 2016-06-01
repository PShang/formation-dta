package fr.pizzeria.dao.client;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public interface IClientDao {
	
	void saveClient(Client client) throws DaoException;
	Client login(String email, String motDePasse) throws DaoException;

}
