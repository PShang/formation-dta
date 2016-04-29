package fr.pizzeria.model;

import java.lang.reflect.Field;

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
//
///*
// * for(Field f: Pizza.class.getDeclaredField()){ System.out.println(F)
// * DesactiveOptionMenu annotation = f.getAnnotation(DesactiverOptionMenu.class)
// * if(annotation==null) {f.get....
// */
//
//public @interface ToString {
//	String attribus() default "";
//}

/*
 * Principales caractéristiques d’une classe– nom,– liste d'attributs – liste de
 * méthodes • Déclaration avec le mot-clé class
 * 
 * [modificateurs] class ExempleDeClasse {attributs et méthodes}
 */
public class Pizza {
	public int id;
	@ToString
	public String code;
	@ToString
	public String nom;
	public double prix;
	public CategoriePizza categorie;

	public static int nbPizzas;

	public Pizza() {
		super();

	}

	public Pizza(String code, String nom, double prix, CategoriePizza cat) {
		this();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = cat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;

	}

	public double getprix() {
		return prix;

	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public static int getNbPizzas() {
		return nbPizzas;
	}

	public static void setNbPizzas(int nbPizzas) {
		Pizza.nbPizzas = nbPizzas;
	}

	public double getPrix() {
		return prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {

		String value = "";
		for (Field champ : this.getClass.getDeclaredField())

		{
			ToString annotation = champ.getAnnotation(ToString.class);
			/*
			 * if(annotation!==null) { try { boolean
			 * attribus=annotation.attribus(); Object
			 * valeurDuChamp=champ.get(this);
			 * 
			 * value+= attribus?valeurDuChamp.toString().toUpperCase() }
			 * catch(IllegalArgumentException) } } }
			 */
			return "Pizza [code=" + code + ", nom=" + nom + ", prix=" + prix + ", categorie=" + categorie + "]";
		}
	}
}
