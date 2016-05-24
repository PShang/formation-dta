package com.sdz.bean;

import java.util.HashSet;
import java.util.Set;

public class Professeur {
	private int id=0;
	private String nom="";
	private String prenom="";
	
	private Set<Matiere> listMatiere = new HashSet<Matiere>();
	
	public Professeur(int id, String nom, String prenom){
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
	}
	
	public Professeur(){}

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
	}

	public Set<Matiere> getListMatiere() {
		return listMatiere;
	}

	public void setListMatiere(Set<Matiere> listMatiere) {
		this.listMatiere = listMatiere;
	}
	// ajouter une matiere a un professeur
	public void addMatiere(Matiere matiere){
		this.listMatiere.add(matiere);	}
	// retire une matiere a un professeur
	public void removeMatiere(Matiere matiere){
		this.listMatiere.remove(matiere);
	}

}
