package fr.pizzeria.model;

import java.lang.reflect.Field;

public class Pizza {

	private int id;
	@ToString private String code;
	@ToString(uppercase = true) private String nom;
	@ToString 
	private double prix;
	@ToString 
	private CategoriePizza categorie;
	
	
	public static int nbPizzas;

	public Pizza() {
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

	/**
	 * Utiliser plutôt getNouveauPrix()
	 * @return
	 */
	@Deprecated
	public double getPrix() {
		return prix;
	}
	
	public double getNouveauPrix() {
		// super algo
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
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
		
		for(Field champ : this.getClass().getDeclaredFields()) {
			ToString annotation = champ.getAnnotation(ToString.class);
			
			if(annotation != null) {
				try {
					boolean uppercase = annotation.uppercase();
					
					Object valeurDuChamp = champ.get(this);
					
					/*
					if(valeurDuChamp instanceof CategoriePizza) {
						CategoriePizza cat = (CategoriePizza) valeurDuChamp;
						value +=  uppercase ? cat.getLibelle().toUpperCase() : cat.getLibelle() + " ";
					} else {
						value +=  uppercase ? valeurDuChamp.toString().toUpperCase() : valeurDuChamp + " ";
					}*/
					value +=  (uppercase ? valeurDuChamp.toString().toUpperCase() : valeurDuChamp) + " ";
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	
	
	
	
	

}
