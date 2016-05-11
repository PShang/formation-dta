package fr.pizzeria.ihm.menu.option;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;
public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE_MENU = "Lister Pizzas";

	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZAS_LIBELLE_MENU);
		this.pizzaDao=pizzaDao;

	}

	@Override
	public boolean execute() {
		System.out.println("Lister Pizza Menu");
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		for(Pizza p:pizzas){
			if(p !=null){
				System.out.println(p.code + "->"+p.nom+"("+p.prix+")");

			}
		}
		
		
		
		return true;
	}

}
/*private static void afficherListePizzas(Pizza[] pizzas) {
	for (int i = 0; i < pizzas.length; i++) {
		if (pizzas[i] != null) {
			System.out.println(pizzas[i].code + " -> " + pizzas[i].nom + " (" + pizzas[i].prix + ")");*/
		
		