package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.option.AfficherPizzaTarifPlusEleveOptionMenu;
import fr.pizzeria.ihm.menu.option.AjouterNouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ImporterBaseDeDonneesOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzaGroupeParCategorieOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

@Component
public class MenuPrincipal extends AbstractMenu {

	private static final String MENU_TITRE_LIBELLE = "Application Pizzeria Console";

	@Autowired
	public MenuPrincipal(Scanner sc, DaoFactory dao) {
		super(MENU_TITRE_LIBELLE, sc, dao);
	}

	@Override
	protected void initialiserOptionsMenu() {
		options.put(1, new ListerPizzaOptionMenu(daoFactory));
		options.put(2, new AjouterNouvellePizzaOptionMenu(scanner, daoFactory));
		options.put(3, new MettreAJourPizzaOptionMenu(scanner, daoFactory));
		options.put(4, new SupprimerPizzaOptionMenu(scanner, daoFactory));
		options.put(5, new ListerPizzaGroupeParCategorieOptionMenu(daoFactory));
		options.put(6, new AfficherPizzaTarifPlusEleveOptionMenu(daoFactory));
		options.put(7, new ImporterBaseDeDonneesOptionMenu(daoFactory));
		options.put(99, new QuitterOptionMenu("Quitter"));
	}
	
}
