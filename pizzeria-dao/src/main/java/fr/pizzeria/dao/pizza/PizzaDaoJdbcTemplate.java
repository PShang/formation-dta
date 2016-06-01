package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJdbcTemplate implements IPizzaDao {

	private static final Logger LOG = Logger.getLogger(PizzaDaoJdbcTemplate.class.toString());

	private JdbcTemplate jdbcTemplate;

	private TransactionTemplate transactionTemplate;

	private TransactionTemplate txTemplate;

	@Autowired
	public PizzaDaoJdbcTemplate(DataSource dataSource, PlatformTransactionManager transactionManager) {
		transactionTemplate = new TransactionTemplate(transactionManager);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		String sql = "SELECT*FROM pizza";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new Pizza(rs.getString("reference"), rs.getString("libelle"),
				rs.getDouble("prix"), CategoriePizza.valueOf(rs.getString("categorie"))));

	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		this.jdbcTemplate.update("insert into pizza(code, nom, prix, catégorie, url_image)VALUES(?,?,?,?,?)",
				newPizza.getCode(), newPizza.getNom(), newPizza.getPrix(), newPizza.getCategorie().name(),
				newPizza.getUrlImage());
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		this.jdbcTemplate.update("update pizza set code=?, nom=?,prix=?,categorie=?,url_image=?", updatePizza.getCode(),
				updatePizza.getNom(), updatePizza.getPrix(), updatePizza.getCategorie().name(),
				updatePizza.getUrlImage());
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		this.jdbcTemplate.update("delete from pizza where code =?", codePizza);
	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		ListUtils.partition(listPizzas, nb).forEach(list -> {

			this.transactionTemplate.execute(status -> {
				list.foreach(p -> this.savePizza(p));
			});
		});

	}

	public Pizza doInTransaction(TransactionStatus status) {
		// TODO Auto-generated method stub
		return null;
	}
}
