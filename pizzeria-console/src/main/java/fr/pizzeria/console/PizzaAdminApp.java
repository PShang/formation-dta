package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFichierImpl;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.menu.Menu;


public class PizzaAdminApp {

	public static void main(String[] args) throws IOException {
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String confString = bundle.getString("dao.impl");
		Integer daoImplConf = Integer.valueOf(confString);
				
		switch (daoImplConf) {
		case 0:
			System.out.println("DAO Mémoire");
			lancerApplication(new PizzaDaoImpl());
			break;
		case 1:
			System.out.println("DAO Fichier");
			lancerApplication(new PizzaDaoFichierImpl());
			break;
		default:
			System.err.println("Aucune configuration Dao trouvée. Le fichier application.properties est-il correctement configuré ?");
			break;
		}
		
	}

	private static void lancerApplication(IPizzaDao dao) {
		try(Scanner sc = new Scanner(System.in)) {
			Menu menu = new Menu(sc,dao);
			menu.afficher();
		}
	}

}
