package fr.pizzeria.ihm.menu.option;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
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
			
			System.out.println("Veuillez saisir la categorie");
			CategoriePizza[]categoriePizzas=CategoriePizza.values();
			for (CategoriePizza cat: categoriePizzas)
			{
				System.out.println(cat.ordinal()+"->"+cat.getlibelle());
			}
			int saisieCategorie=sc.nextInt();
			p.setCategorie(categoriePizzas[saisieCategorie]);
			
			pizzaDao.savePizza(p);
			System.out.println("Nouvelle pizza créée");
			
			
			}
			catch (InputMismatchException e) {
				System.out.println("Input"+sc.next()+"n'est pas un nombre");}
				catch (DaoException e) 
				{
					System.err.println("Echec création de pizza");
				}
				/*e.printStackTrace();*/
			
		return true;
	}


}