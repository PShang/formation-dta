package com.sdz.bean;

public class Matiere {

	private int id=0;
	
	private String nom="";
	public Matiere(int id, String nom)
	{
		this.setId(id);
		this.setNom(nom);
	}
	
	public Matiere(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
