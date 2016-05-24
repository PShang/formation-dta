package com.sdz.bean;

import java.util.HashSet;
import java.util.Set;

public class Classe {

	private int id= 0;
	private String nom = "";
	private Set<Professeur> listProfesseur = new HashSet<Professeur>();
	private Set<Eleve> listEleve = new HashSet<Eleve>();
	
	public Classe(int id, String nom){
		this.id=id;
		this.nom=nom;
	}
	
	public Classe(){}

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

	public Set<Professeur> getListProfesseur() {
		return listProfesseur;
	}

	public void setListProfesseur(Set<Professeur> listProfesseur) {
		this.listProfesseur = listProfesseur;
	}

	public Set<Eleve> getListEleve() {
		return listEleve;
	}

	public void setListEleve(Set<Eleve> listEleve) {
		this.listEleve = listEleve;
	}
	
	//ajouter un élève à la classe
	public void addEleve(Eleve eleve){
		if(!this.listEleve.contains(eleve))
			this.listEleve.add(eleve);
	}
	//retirer un élève de la classe
	public void removeEleve(Eleve eleve){
		this.listEleve.remove(eleve);
		
	}
public boolean equals(Classe cls){
	return this.getId()==cls.getId();
}
}

