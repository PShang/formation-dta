package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public class InscrireOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "S'inscrire";

	public InscrireOptionMenu(Scanner scanner, DaoFactory daoFactory) {
		super(LISTER_PIZZAS_LIBELLE_MENU, daoFactory, scanner);
	}

	@Override
	public boolean execute() {
		System.out.println("Veuillez saisir votre nom");
		String nom = scanner.next();
		System.out.println("Veuillez saisir votre prenom");
		String prenom = scanner.next();
		System.out.println("Veuillez saisir votre email");
		String email = scanner.next();
		System.out.println("Veuillez saisir votre mot de passe");
		String motDePasse = scanner.next();
		
		Client client = new Client(nom, prenom, email, motDePasse);
		
		try {
			daoFactory.getClientDao().saveClient(client);
		} catch (DaoException e) {
			System.out.println("Erreur lors de la création de votre compte. Votre email existe déjà ?");
		}
	

		return true;
	}

}
