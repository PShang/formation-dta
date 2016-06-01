package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public class ListerCommandeOptionMenu extends AbstractOptionMenu {
	private Client client;

	public ListerCommandeOptionMenu(Client client, Scanner sc, DaoFactory daoFactory) {
		super("Lister ses commandes", daoFactory, sc);
		this.client = client;
	}

	@Override
	public boolean execute() {
		try {
			daoFactory.getCommandeDao().listerCommande(client.getId()).forEach(System.out::println);
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		return true;
	}


}
