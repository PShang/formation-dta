package fr.pizzeria.ihm.menu.option;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;

public class CommanderPizzaOptionMenu extends AbstractOptionMenu {

	private Client client;

	public CommanderPizzaOptionMenu(Client client, DaoFactory daoFactory, Scanner sc) {
		super("Commander une pizza", daoFactory, sc);
		this.client = client;
	}

	@Override
	public boolean execute() {
		try {
			System.out.println("Veuillez selectionner les pizzas");
			List<Pizza> allPizzas = daoFactory.getPizzaDao().findAllPizzas();
			
			allPizzas.forEach(System.out::println);
			String saisie = scanner.next();
			
			Optional<Pizza> pizzaOpt = allPizzas.stream().filter(p -> p.getCode().equals(saisie)).findFirst();
			
			
			Commande commande = new Commande();
			commande.setClient(client);
			commande.addPizza(pizzaOpt.get());
			daoFactory.getCommandeDao().saveCommande(commande);
			
		} catch (DaoException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}

}
