package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Repository
public class PizzaDaoJdbcTemplate implements IPizzaDao {

	private static final Logger LOG = Logger.getLogger(PizzaDaoJdbcTemplate.class.toString());

	private JdbcTemplate jdbcTemplate;

	private TransactionTemplate txTemplate;

	private RowMapper<Pizza> rowMapperPizza = (rs, rowNum) -> new Pizza(rs.getString("code"), rs.getString("nom"),
			rs.getBigDecimal("prix"), CategoriePizza.valueOf(rs.getString("categorie")));

	@Autowired
	public PizzaDaoJdbcTemplate(DataSource dataSource, PlatformTransactionManager txManager) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.txTemplate = new TransactionTemplate(txManager);
		LOG.log(Level.INFO, "Création du bean PizzaDaoJdbcTemplate");
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		return this.jdbcTemplate.query("select * from pizza", rowMapperPizza);
	}

	@Override
	public Pizza findOnePizza(String code) throws DaoException {
		try {
			return this.jdbcTemplate.queryForObject("select * from pizza where code=?", rowMapperPizza, code);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void savePizza(Pizza newPizza) {
		this.jdbcTemplate.update("insert into pizza (code,nom,prix,categorie,url_image) VALUES(?,?,?,?,?)",
				newPizza.getCode(), newPizza.getNom(), newPizza.getPrix(), newPizza.getCategorie().name(),
				newPizza.getUrlImage());
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		this.jdbcTemplate.update("update pizza set code = ?, nom = ?,prix=?,categorie=?,url_image=? where code=?",
				updatePizza.getCode(), updatePizza.getNom(), updatePizza.getPrix(), updatePizza.getCategorie().name(),
				updatePizza.getUrlImage(), codePizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		this.jdbcTemplate.update("delete from pizza where code=?", codePizza);
	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {

		ListUtils.partition(listPizzas, nb).forEach(list -> {

			/*
			 * this.txTemplate.execute(new TransactionCallback<Pizza>() {
			 * 
			 * @Override public Pizza doInTransaction(TransactionStatus status)
			 * { return null; } });
			 */

			this.txTemplate.execute(status -> {
				list.forEach(p -> this.savePizza(p));
				return null;
			});

		});

	}

	
}