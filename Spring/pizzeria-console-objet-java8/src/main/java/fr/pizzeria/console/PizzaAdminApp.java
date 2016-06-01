package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.DaoProducer;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.MenuPrincipal;


public class PizzaAdminApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException, DaoException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String confString = bundle.getString("dao.impl");
		Integer daoImplConf = Integer.valueOf(confString);
				
		switch (daoImplConf) {
		case 0:
			System.out.println("DAO Mémoire");
			lancerApplication(new DaoProducer().getDaoFactoryMemoire());
			break;
		case 1:
			System.out.println("DAO Fichier");
			lancerApplication(new DaoProducer().getDaoFactoryFichier());
			break;
		case 2:
			System.out.println("DAO Jdbc");
			ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");
			String driver = jdbcBundle.getString("jdbc.driver");
			String url = jdbcBundle.getString("jdbc.url");
			String user = jdbcBundle.getString("jdbc.user");
			String pass = jdbcBundle.getString("jdbc.pass");
			
			lancerApplication(new DaoProducer().getDaoFactoryJdbc(driver, url, user, pass));
			break;
		case 3:
			System.out.println("DAO JPA");
			//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-pu");
			lancerApplication(new DaoProducer().getDaoFactoryJpa(emf));
			emf.close();
			break;
		default:
			System.err.println("Aucune configuration Dao trouvée. Le fichier application.properties est-il correctement configuré ?");
			break;
		}
		
	}

	private static void lancerApplication(DaoFactory dao) {
		try(Scanner sc = new Scanner(System.in)) {
			MenuPrincipal menu = new MenuPrincipal(sc,dao);
			menu.afficher();
		}
	}

}
