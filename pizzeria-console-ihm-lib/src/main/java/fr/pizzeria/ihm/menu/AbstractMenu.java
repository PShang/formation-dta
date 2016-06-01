package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.option.AbstractOptionMenu;

public abstract class AbstractMenu {

	protected Map<Integer, AbstractOptionMenu> options = new TreeMap<>();
	protected Scanner scanner;
	protected DaoFactory daoFactory;
	protected String titre;

	public AbstractMenu(String titre, Scanner sc, DaoFactory daoFactory) {
		super();
		this.titre = titre;
		this.daoFactory = daoFactory;
		this.scanner = sc;
		initialiserOptionsMenu();
	}
	
	protected abstract void initialiserOptionsMenu();
	
	public void afficher() {
		boolean continuer = true;
		while (continuer) {
			System.out.println("**** " + this.titre + " ****");
			
			options.forEach((cle,valeur) -> System.out.println(cle + ". " + valeur.getLibelle()));
						
			int saisie = scanner.nextInt();
			continuer = options.get(saisie).execute();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
