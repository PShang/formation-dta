package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;

public abstract class AbstractOptionMenu {
	private String libelle;
	protected DaoFactory daoFactory;
	protected Scanner scanner;
	
	public AbstractOptionMenu(String libelle, DaoFactory daoFactory, Scanner sc) {
		this.scanner = sc;
		this.libelle = libelle;
		this.daoFactory = daoFactory;
	}

	public abstract boolean execute();

	public String getLibelle() {
		return libelle;
	}
	
}
