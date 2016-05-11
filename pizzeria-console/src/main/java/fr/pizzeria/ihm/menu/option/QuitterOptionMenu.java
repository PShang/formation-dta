package fr.pizzeria.ihm.menu.option;

public class QuitterOptionMenu extends AbstractOptionMenu {

	private static final String QUITTER_LIBELLE_MENU = "Quitter";

	public QuitterOptionMenu() {
		super(QUITTER_LIBELLE_MENU);
	}

	@Override
	public boolean execute() {
		System.out.println("Aurevoir :-(");
		return false;
	}

}
