package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.option.CommanderPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerCommandeOptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;
import fr.pizzeria.model.Client;

public class MenuClientConnecte extends AbstractMenu {
	

	public MenuClientConnecte(Client client,Scanner sc, DaoFactory daoFactory) {
		super("", sc, daoFactory);
		options.put(1, new CommanderPizzaOptionMenu(client, daoFactory, scanner));
		options.put(2, new ListerCommandeOptionMenu(client, scanner, daoFactory));
	}

	@Override
	protected void initialiserOptionsMenu() {
		options.put(99, new QuitterOptionMenu("Sortir"));

	}

}
