package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.option.ConnecterOptionMenu;
import fr.pizzeria.ihm.menu.option.InscrireOptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;

public class MenuPrincipal extends AbstractMenu {

	private static final String MENU_TITRE_LIBELLE = "Application Pizzeria Client";

	public MenuPrincipal(Scanner sc, DaoFactory daoFactory) {
		super(MENU_TITRE_LIBELLE, sc, daoFactory);
	}
	@Override
	protected void initialiserOptionsMenu() {
		options.put(1, new InscrireOptionMenu(scanner, daoFactory));
		options.put(2, new ConnecterOptionMenu(scanner, daoFactory));
		options.put(99, new QuitterOptionMenu("Sortir"));		
	}

	
}
