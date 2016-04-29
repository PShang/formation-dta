package fr.pizzeria.console;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaompl;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.model.Pizza;

public class PizzaAdminApp {

	public static void main(String[] args) throws DeletePizzaException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		IPizzaDao dao=new PizzaDaompl(); 
		
		Menu menu = new Menu(sc,dao);
		menu.afficher(); 
	}

	}
