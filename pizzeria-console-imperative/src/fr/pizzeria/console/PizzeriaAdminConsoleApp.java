package fr.pizzeria.console;

import java.util.Scanner;


public class PizzeriaAdminConsoleApp {
	
	private static Object[][] getTableauPizzas() {
	
Object[][]pizzas=new Object [100][3];
pizzas[0]=new Object[]{"PEP","Pépéroni",12.50};
pizzas[1]=new Object[]{"MAR","Margherita",14.00};
pizzas[2]=new Object[]{"REI","La Reine",11.50};
pizzas[3]=new Object[]{"FRO","La 4 fromages",12.00};
pizzas[4]=new Object[]{"CAN","La cannibale",12.50};
pizzas[5]=new Object[]{"SAV","La savoyarde",13.00};
pizzas[6]=new Object[]{"ORI","L'orientale",13.50};
pizzas[7]=new Object[]{"IND","L'indienne",14.00};
return pizzas;
	}
	
	
	public static void main(String [] args) {
	System.out.println("Menu de Pizzeria");
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Lister les pizzas\n"
		+ "2.Ajouter une nouvelle pizza\n"
				+ "3.Mettre à jour une pizza\n"
				+ "4.Supprimer une pizza\n"
				+ "99.Sortir\n");
	
	System.out.println("Veuillez faire votre choix de 1 à 99");
	int n = (int) sc.nextInt();
	if (n==1)
	{
		System.out.println("Liste des pizzas");
	
	System.out.println("1.Lister les pizzas\n"
			+ "2.Ajouter une nouvelle pizza\n"
			+ "3.Mettre à jour une pizza\n"
			+ "4.Supprimer une pizza\n"
			+ "99.Sortir\n");}
	else if (n==2)
	{
		System.out.println("Ajout d'une nouvelle pizza");
		System.out.println("1.Lister les pizzas\n"
				+ "2.Ajouter une nouvelle pizza\n"
				+ "3.Mettre à jour une pizza\n"
				+ "4.Supprimer une pizza\n"
				+ "99.Sortir\n");}
	else if (n==3)
	{
		System.out.println("Mise à jour d'une pizza\n");
	System.out.println("1.Lister les pizzas\n"
			+ "2.Ajouter une nouvelle pizza\n"
			+ "3.Mettre à jour une pizza\n"
			+ "4.Supprimer une pizza\n"
			+ "99.Sortir\n");}
	else if (n==4)
	{
		System.out.println("Suppression d'une pizza\n");
		System.out.println("1.Lister les pizzas\n"
				+ "2.Ajouter une nouvelle pizza\n"
				+ "3.Mettre à jour une pizza\n"
				+ "4.Supprimer une pizza\n"
				+ "99.Sortir\n");}
	else if (n==5)
	{
		System.out.println("Au revoir =(");
		sc.close();
		
	}
	}
	
			}

