package fr.pizzeria.ihm.menu.option;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

public class AjouterUneNouvellePizza extends AbstractOptionMenu {

	private static final String AJOUTER_PIZZA_LIBELLE_MENU = "Ajouter une nouvelle pizza";

	public AjouterUneNouvellePizza(Scanner scanner,IPizzaDao pizzaDao) {
		super(AJOUTER_PIZZA_LIBELLE_MENU, pizzaDao,scanner);

	}

	@Override
	public boolean execute() {
		
			Pizza p = new Pizza();
			System.out.println("Veuillez saisir le code");
			p.code = sc.next();
			System.out.println("Veuillez saisir le nom (sans espace)");
			p.nom = sc.next();
			System.out.println("Veuillez saisir le prix");
			p.prix = sc.nextDouble();
			
			try
			{
				pizzaDao.savePizza(p);
			
			System.out.println("Nouvelle pizza créée");
			}
			catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}


}