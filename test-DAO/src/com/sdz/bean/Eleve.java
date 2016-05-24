package com.sdz.bean;

public class Eleve {

	private int id = 0;
	private String nom = "";
	private String prenom = "";

	public Eleve(int id, String nom, String prenom) {
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);

	}

	public Eleve() {
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	};

}
