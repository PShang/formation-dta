package fr.pizzeria.dao.pizza;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoSpring implements IPizzaDao {

	private static final Logger LOG = Logger.getLogger(PizzaDaoSpring.class.toString());

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private BatchPizzaDaoJpaSpring batchPizzaDaoJpaSpring;

	public PizzaDaoSpring() {
		super();
		LOG.log(Level.INFO, "création du bean PizzaDaoSpring");
	}

	@Override
	public Pizza findOnePizza(String code) throws DaoException {
		try {
			TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code= : codePizza", Pizza.class);
			query.setParameter("codePizza", code);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

	@Override
	@Transactional
	public void savePizza(Pizza newPizza) throws DaoException {
		em.persist(newPizza);
	}

	@Override
	@Transactional
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {

		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code=:codePizza", Pizza.class);
		query.setParameter("codePizza", codePizza);
		Pizza pizza = query.getSingleResult();
		pizza.setCode(updatePizza.getCode());
		pizza.setNom(updatePizza.getNom());
		pizza.setPrix(updatePizza.getPrix());
		pizza.setCategorie(updatePizza.getCategorie());

	}

	@Override
	@Transactional
	public void deletePizza(String codePizza) throws DaoException {

		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code=:codePizza", Pizza.class);
		query.setParameter("codePizza", codePizza);
		Pizza pizza = query.getSingleResult();
		em.remove(pizza);
	}

	@Override
	@Transactional
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {

		ListUtils.partition(listPizzas, nb).forEach(batchPizzaDaoJpaSpring::save);

	}

}