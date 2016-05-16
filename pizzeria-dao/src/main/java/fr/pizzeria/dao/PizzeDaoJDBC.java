package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.ListUI;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzeDaoJDBC implements IPizzaDao {
	private String url;
	private String user;
	private String pass;
	private Object ListUtils;

	public PizzeDaoJDBC(String driver, String url2, String user2, String pass2) throws DaoException {
		try {
			Class.forName("com.mysqi.jdbc.Driver");
			this.url = url2;
			this.user = user2;
			this.pass = pass2;

		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		}
	}

	private Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, user, pass);
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		List<Pizza> pizzas = new ArrayList<>();
		

		try {
			Connection connection = getConnection();
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria", "shang", "mysql");

			System.out.println("Successfully connected to database");

			Statement statement = connection.createStatement();

			ResultSet resultats = statement.executeQuery("SELECT * FROM PIZZA");
			{

				while (resultats.next()) {
					Pizza pizza = new Pizza();

					pizza.setId(resultats.getInt("identifie"));

					pizza.setNom(resultats.getString("nom"));

					pizza.setPrix(resultats.getInt("prix"));

					Pizza.setCategorie(CategoriePizza.valueOf(resultats.getString("categorie")));

				}

			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return pizzas;
	}

	@Override
	public void saveAllPizza(List<Pizza>listPizzasFichier, int nbGroup ) throws DaoException {
		listPizzasFichier.sort(Comparator.comparing(Pizza::getCode));
		List<List<Pizza>> listPartitionnee= ListUtils.partition(listPizzasFichier,nbGroup);
		
		try (Connection connection = getConnection(); )
				{
					connection.setAutoCommit(false);
					insertListerPizza(listPartitionnee,connection);
			}

		catch (SQLException e) {
			throw new SavePizzaException(e);
		}
	}

	@Override
	public void insertListerPizza (List<List<Pizza>> listPartitionnee,Connection connection) throws DaoException, SQLException {
		try{
			for (List<Pizza> list: listPartitionnee){
				for(Pizza p : list){
					insertListerPizza(connection, p);
				}
				connection.commit();
			}
		}catch (DaoException e) {
			connection.rollback();
			throw e;
		}
				
		
				
				
				
				
				
				
				
				
				
				PreparedStatement st = connection.createStatement("insert into pizza(code,nom,prix,categorie) VALUES(?,?,")
				st.setString(1, newPizza.getCode());
				st.setString(1, newPizza.getCode());
				st.setString(1, newPizza.getCode());
				st.setString(1, newPizza.getCode());
				
			int nbLignesAffectes = st.executeUpdate(String.format("update pizza set code='%s',nom='%s',prix=%s,categorie='%s' where code='%s'",updatePizza.getCode(), updatePizza.getNom(), updatePizza.getPrix(), updatePizza.getCategorie().name(), codePizza));

			if (nbLignesAffectes == 0) {
				throw new UpdatePizzaException("Aucune ligne mise à jour en base de données");
			}

		} catch (private void insertListerPizza(Connection connection, Pizza p) {
		// TODO Auto-generated method stub
		
	}
		SQLException e) {
			throw new UpdatePizzaException(e);
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		try (Connection connection = getConnection(); Statement st = connection.createStatement();) {
			int nbLignesAffectes = st.executeUpdate(String.format("delete from pizza where code='%s'", codePizza));

			if (nbLignesAffectes == 0) {
				throw new DeletePizzaException("Aucune ligne supprimée en base de données");
			}

		} catch (SQLException e) {
			throw new DeletePizzaException(e);
		}

	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		// TODO Auto-generated method stub
		
	}
}