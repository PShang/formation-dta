package fr.pizzeria.ihm.menu.option;


public class QuitterOptionMenu extends AbstractOptionMenu {
	

	public QuitterOptionMenu(String libelle) {
		super(libelle, null, null);
	}

	@Override
	public boolean execute() {
		System.out.println("Aurevoir :-(");
		return false;
	}

}
