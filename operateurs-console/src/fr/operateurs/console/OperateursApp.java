package fr.operateurs.console;

import java.util.Scanner;

public class OperateursApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println("ghooo");
		 Scanner sc=new Scanner(System.in);
		 
		 System.out.println("Veuiilez saisir le premier nombre");
		 double nb = sc.nextDouble();
		 System.out.println("NB="+nb);
		 
		 System.out.println("Veuillez saisir le second nombre ");
		 double nb1 = sc.nextDouble();
		 System.out.println("NB="+nb1);
		 
		 System.out.println(nb+"+"+nb1+"="+( nb+nb1));
		 System.out.println(nb+"-"+nb1+"="+( nb-nb1));
		 System.out.println(nb+"*"+nb1+"="+( nb*nb1));
		 System.out.println(nb+"/"+nb1+"="+( nb/nb1));
		 System.out.println(nb+"%"+nb1+"="+( nb%nb1));
		 
		 sc.close();
	}

}
