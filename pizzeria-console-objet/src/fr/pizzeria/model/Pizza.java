package fr.pizzeria.model;
/*Principales caractéristiques d’une classe

– nom,– liste d'attributs – liste de méthodes

• Déclaration avec le mot-clé class

[modificateurs] class ExempleDeClasse {

 attributs et méthodes}*/
public class Pizza {
	public int id;
	public String code;
	public String nom;
	public double prix;
	
	public static int nbPizzas;
	public Pizza() {
		super();
		//TODO Auto-generated constructeur
		//this("jjj","hhhh",12.0);
		
	}
	public Pizza(String code, String nom, double prix)
	{
		this();
		this.code=code;
		this.nom=nom;
		this.prix=prix;
	}

	public int getId(){
		return id;}
	
	
public void setId(int id){
	this.id=id;
}

	
	public String getCode(){
		return code;
	}
	
	public void setCode(String code){
		this.code=code;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
		
	}
	
	public double getprix()
	{
		return prix;	
		
	}
	
	public void setPrix(double prix){
		this.prix=prix;
	}
}


