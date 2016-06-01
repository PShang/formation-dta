package fr.pizzeria.dao.commande;

import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Commande;

public interface ICommandeDao {
	void saveCommande(Commande newCommande) throws DaoException;
	List<Commande> listerCommande(Integer idClient) throws DaoException;
}
