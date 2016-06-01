package fr.pizzeria.console;

public class Voiture {

	static String marque = "ddd";
	int a;
	boolean b;
	
	static {
		System.out.println("Hello static");
	}

	public static void main(String[] args) {
		System.out.println(Voiture.marque);

		Voiture v = new Voiture(); 
		System.out.println(v.marque);
		
		
		String a = "a1";
		String b = "a1";
		b+="";
		String c = new String(a);
		String d = new String("a1");
		String e = "a1";
		/*System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(c == d);
		System.out.println(e == b);
		System.out.println(a.equals(c));
*/
		

		

	}

}
