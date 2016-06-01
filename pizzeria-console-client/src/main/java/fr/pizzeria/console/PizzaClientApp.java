package fr.pizzeria.console;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.DaoProducer;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.MenuPrincipal;


public class PizzaClientApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException, DaoException {
			Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-pu");
			lancerApplication(new DaoProducer().getDaoFactoryJpa(emf));
			emf.close();
	}

	private static void lancerApplication(DaoFactory dao) {
		try(Scanner sc = new Scanner(System.in)) {
			MenuPrincipal menu = new MenuPrincipal(sc,dao);
			menu.afficher();
		}
	}

}
