package fr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	public String nom;
	public String prenom;
	private String motDePasse;
	public String mail;

	
	public Client(int id, String nom, String prenom, String mail, String motDePasse)
	{
	super();
	this.id=id;
	this.nom=nom;
	this.prenom=prenom;
	this.mail=mail;
	this.motDePasse=motDePasse;
}
	public Client(String nom, String prenom, String mail, String motDePasse)
	{
		super();
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.motDePasse=motDePasse;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getEmail() {
		return mail;
	}
	public void setEmail(String email) {
		this.mail = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}