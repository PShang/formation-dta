package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.MenuClientConnecte;
import fr.pizzeria.model.Client;

public class ConnecterOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Se connecter";


	public ConnecterOptionMenu(Scanner scanner, DaoFactory daoFactory) {
		super(LISTER_PIZZAS_LIBELLE_MENU, daoFactory, scanner);
	}

	@Override
	public boolean execute() {
		System.out.println("Veuillez saisir votre email");
		String email = scanner.next();
		System.out.println("Veuillez saisir votre mot de passe");
		String motDePasse = scanner.next();
		
		try {
			Client client = daoFactory.getClientDao().login(email, motDePasse);
			System.out.println(String.format("Vous êtes %s %s", client.getPrenom(), client.getNom()));
			new MenuClientConnecte(client, scanner, daoFactory).afficher();
		} catch (DaoException e) {
			System.out.println("Email ou mot de passe incorrect");
		}
	

		return true;
	}

}
