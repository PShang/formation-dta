package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class List {
	public static void main(String[] args) {
		Pizza pizza1 = new Pizza("code1", "nom1", 12);
		Pizza pizza2= new Pizza("code2", "nom2", 14);
		Pizza pizza3 = new Pizza("code3", "nom3", 13);

		ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();

		pizzalist.add(pizza1);
		pizzalist.add(pizza2);
		pizzalist.add(pizza3);

		for (Pizza p : pizzalist) {

			System.out.println(p.getCode());
			if (p.getCode().equals("code1")) {
				// piazzalist.remove(p);
			}
		}
		Iterator<Pizza> itPizzaList = pizzalist.iterator();
		while (itPizzaList.hasNext())

		{
			Pizza pizza = itPizzaList.next();
			System.out.println(pizza.getNom());
		}
		Set<Pizza> pizzaSet = new HashSet<Pizza>();
		pizzaSet.add(pizza1);
		pizzaSet.add(pizza2);
		pizzaSet.add(pizza3);}
}
		
	