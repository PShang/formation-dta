import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PizzeriaClientApp {

	public static Object[][] getTableauInformations() {
		Object[][] informations = new Object[10][5];
		informations[0] = new Object[] { "nom", "prenom", "mail", "mot de passe" };
		informations[1] = new Object[] { "mail", "mot de passe" };
		return informations;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Object[][] informations = getTableauInformations();

		int saisie = 0;
		do {

			afficherMenu();
			saisie = sc.nextInt();

			switch (saisie) {
			case 1:
				demanderInformationClient(sc, informations);
				break;
			case 2:
				demanderConnectionClient(sc, informations);
				break;
			case 99:
				System.out.println("Au revoir!");
				break;

			}

		} while (saisie != 99);
		sc.close();
	}

	private static Object[] demanderInformationClient(Scanner sc, Object[][] informations) {
		System.out.println("Veuillez saisir votre nom de famille");
		String newNom = sc.next();
		System.out.println("Veuillez saisir votre prenom");
		String newPrenom = sc.next();
		System.out.println("Veuillez saisir votre adresse mail");
		String newMail = sc.next();
		System.out.println("Veuillez saisir votre mot de passe");
		String newMotDePasse = sc.next();
		System.out.println("Vous êtes" + " " + newPrenom + " " + newNom);
		return new Object[] { newNom, newPrenom, newMail, newMotDePasse };

	}

	private static Object[] demanderConnectionClient(Scanner sc, Object[][] informations) {
		System.out.println("Veuillez saisir votre adresse mail");
		String newMail = sc.next();
		System.out.println("Veuillez saisir votre mot de passe");
		String newMotdepasse = sc.next();
		return new Object[] { newMail, newMotdepasse };

	}

	public static void afficherMenu() {

		System.out.println("*****Pizzeria Client*****");
		System.out.println("1. S'inscrire");
		System.out.println("2. Se connecter");
		System.out.println("99. Sortir");
	}
	/*
	 * EntityManagerFactory emf =
	 * Persistence.createEntityFactory("pizzeria-console");
	 */
}