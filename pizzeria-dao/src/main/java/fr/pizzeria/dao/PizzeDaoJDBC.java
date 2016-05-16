package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.sql.PreparedStatement;
import org.apache.commons.collections4.ListUtils;

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
			try (Connection connection = getConnection();
					Statement st = connection.createStatement();
					ResultSet result = st.executeQuery("select * from pizza");) {
				while (result.next()) {
					Pizza pizza = new Pizza();
					pizza.setCode(result.getString("code"));
					pizza.setId(result.getInt("id"));
					pizza.setNom(result.getString("nom"));
					pizza.setPrix(result.getDouble("prix"));
					pizza.setCategorie(CategoriePizza.valueOf(result.getString("categorie")));
					pizzas.add(pizza);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			return pizzas;
		}

		@Override
		public void savePizza(Pizza newPizza) throws DaoException {
			try (Connection connection = getConnection();) {
				insertPizza(connection, newPizza);

			} catch (SQLException e) {
				throw new SavePizzaException(e);
			}
		}

		private void insertPizza(Connection connection, Pizza newPizza) throws DaoException {
			try (PreparedStatement st = connection.prepareStatement("insert into pizza(code,nom,prix,categorie) VALUES(?,?,?,?)");) {
				st.setString(1, newPizza.getCode());
				st.setString(2, newPizza.getNom());
				st.setDouble(3, newPizza.getPrix());
				st.setString(4, newPizza.getCategorie().name());

				int nbLignesAffectes = st.executeUpdate();

				if (nbLignesAffectes == 0) {
					throw new SavePizzaException("Aucune ligne insérée en base de données");
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}

		@Override
		public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
			try (Connection connection = getConnection(); Statement st = connection.createStatement();) {
				int nbLignesAffectes = st.executeUpdate(String.format("update pizza set code='%s',nom='%s',prix=%s,categorie='%s' where code='%s'",
						updatePizza.getCode(), updatePizza.getNom(), updatePizza.getPrix(), updatePizza.getCategorie().name(), codePizza));

				if (nbLignesAffectes == 0) {
					throw new UpdatePizzaException("Aucune ligne mise à jour en base de données");
				}

			} catch (SQLException e) {
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
		public void saveAllPizzas(List<Pizza> listPizzasFichier, int nbGroup) throws DaoException {

			listPizzasFichier.sort(Comparator.comparing(Pizza::getCode));

			List<List<Pizza>> listPartitionnee = ListUtils.partition(listPizzasFichier, nbGroup);

			try (Connection connection = getConnection();) {
				connection.setAutoCommit(false);
				insertListerPizzas(listPartitionnee, connection);

			} catch (SQLException e) {
				throw new DaoException(e);
			}

		}

		private void insertListerPizzas(List<List<Pizza>> listPartitionnee, Connection connection) throws SQLException, DaoException {
			try {
				for (List<Pizza> list : listPartitionnee) {
					for (Pizza p : list) {
						insertPizza(connection, p);
					}
					connection.commit();
				}
			} catch (DaoException e) {
				connection.rollback();
				throw e;
			}
		}
		@Override
		public void saveAllPizza(List<Pizza> listPizzasFichier, int nbGroup) throws DaoException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void insertListerPizza(List<List<Pizza>> listPartitionnee, Connection connection)
				throws DaoException, SQLException {
			// TODO Auto-generated method stub
			
		}

	}

