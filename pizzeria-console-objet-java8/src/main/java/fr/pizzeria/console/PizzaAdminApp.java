package fr.pizzeria.console;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.MenuPrincipal;

public class PizzaAdminApp {

	private PizzaAdminApp() {
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, DaoException {
		Logger.getLogger("org").setLevel(Level.SEVERE);

		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PizzeriaAppSpringConfig.class)) {
			MenuPrincipal menu = context.getBean(MenuPrincipal.class);

			menu.afficher();
		};
		
	}

}
