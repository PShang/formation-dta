package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	private static Object[][] getTableauPizzas() {
		Object[][] pizzas = new Object[100][3];
		pizzas[0] = new Object[] { "PEP", "Pépéroni", 12.50 };
		pizzas[1] = new Object[] { "MAR", "Margherita", 14.00 };
		pizzas[2] = new Object[] { "REI", "La Reine", 11.50 };
		pizzas[3] = new Object[] { "FRO", "La 4 fromages", 12.00 };
		pizzas[4] = new Object[] { "CAN", "La cannibale", 12.50 };
		pizzas[5] = new Object[] { "SAV", "La savoyarde", 13.00 };
		pizzas[6] = new Object[] { "ORI", "L'orientale", 13.50 };
		pizzas[7] = new Object[] { "IND", "L'indienne", 14.00 };
		return pizzas;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Object[][] pizzas = getTableauPizzas();

		int saisie = 0;
		do {
			afficherMenuPrincipal();

			saisie = sc.nextInt();

			switch (saisie) {
			case 1:
				listerPizzas(pizzas);
				break;
			case 2:
				ajouterNouvellePizza(sc, pizzas);
				break;
			case 3:
				MettreAJourUnePizza(sc, pizzas);
				break;
			case 4:
				supprimerPizza(sc, pizzas);
				break;
			case 99:
				System.out.println("Aurevoir :-(");
				break;
			}
		} while (saisie != 99);

		sc.close();
	}

	private static void afficherMenuPrincipal() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas ");
		System.out.println("2. Ajouter une nouvelle pizza ");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

	private static void listerPizzas(Object[][] pizzas) {
		System.out.println("Liste des pizzas");
		afficherListePizzas(pizzas);
	}

	private static void ajouterNouvellePizza(Scanner sc, Object[][] pizzas) {
		System.out.println("Ajout d’une nouvelle pizza");
		boolean placeTrouve = false;
		int index = 0;
		while (!placeTrouve && index < pizzas.length) {
			placeTrouve = pizzas[index][0] == null;
			index++;
		}

		if (placeTrouve) {
			pizzas[index] = saisirDonneesPizza(sc);
		} else {
			System.err.println("Plus de place pour une nouvelle pizza");
		}
	}

	private static void MettreAJourUnePizza(Scanner sc, Object[][] pizzas) {
		System.out.println("Mise à jour d’une pizza");
		afficherListePizzas(pizzas);
		System.out.println("Veuillez choisir la pizza à modifier. (99 pour abandonner)");
		String codePizza = sc.next();
		Object[] resultatRecherche = rechercherPizza(pizzas, codePizza);
		boolean pizzaTrouve = (boolean) resultatRecherche[0];
		int indexPizzaTrouve = (int) resultatRecherche[1];

		if (pizzaTrouve) {
			pizzas[indexPizzaTrouve] = saisirDonneesPizza(sc);
		} else {
			System.err.println("Code pizza non trouvé");
		}
	}

	private static void supprimerPizza(Scanner sc, Object[][] pizzas) {
		System.out.println("Suppression d’une pizza");
		afficherListePizzas(pizzas);
		System.out.println("Veuillez choisir la pizza à supprimer. (99 pour abandonner)");
		String codePizzaSuppr = sc.next();
		Object[] resultatRecherche = rechercherPizza(pizzas, codePizzaSuppr);
		boolean pizzaTrouve = (boolean) resultatRecherche[0];
		int indexPizzaTrouve = (int) resultatRecherche[1];
		if (pizzaTrouve) {
			pizzas[indexPizzaTrouve] = new Object[3];
		} else {
			System.err.println("Code pizza non trouvé");
		}
	}

	private static void afficherListePizzas(Object[][] pizzas) {
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i][0] != null) {
				System.out.println(pizzas[i][0] + " -> " + pizzas[i][1] + " (" + pizzas[i][2] + ")");
			}
		}
	}

	private static Object[] rechercherPizza(Object[][] pizzas, String codePizza) {
		boolean pizzaTrouve = false;
		int indexPizzaTrouve = 0;
		while (!pizzaTrouve && indexPizzaTrouve < pizzas.length) {
			pizzaTrouve = codePizza.equals(pizzas[indexPizzaTrouve][0]);
			if (!pizzaTrouve) {
				indexPizzaTrouve++;
			}
		}
		return new Object[] { pizzaTrouve, indexPizzaTrouve };
	}

	private static Object[] saisirDonneesPizza(Scanner sc) {
		System.out.println("Veuillez saisir le code");
		String newCode = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String newNom = sc.next();
		System.out.println("Veuillez saisir le prix");
		double newPrix = sc.nextDouble();
		return new Object[] { newCode, newNom, newPrix };
	}

}
