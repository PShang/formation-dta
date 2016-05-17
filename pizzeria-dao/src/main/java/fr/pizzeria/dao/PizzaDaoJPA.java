package fr.pizzeria.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA implements IPizzaDao {
	private EntityManagerFactory emf;

	public PizzaDaoJPA(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		EntityManager em = emf.createEntityManager();
		List<Pizza> listPizzas = em.createQuery("select p from Pizza p", Pizza.class).getResultList();
		em.clear();
		return listPizzas;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPizza);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code=:codePizza", Pizza.class);
			query.setParameter("codePizza", codePizza);
			Pizza pizza = query.getSingleResult();
			pizza.setCategorie(updatePizza.getCategorie());
			pizza.setCode(updatePizza.getCode());
			pizza.setNom(updatePizza.getNom());
			pizza.setPrix(updatePizza.getPrix());
			em.getTransaction().commit();

		} finally {
			em.close();
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code =:codepizza", Pizza.class);
			query.setParameter("codePizza", codePizza);
			Pizza pizza = query.getSingleResult();
			em.remove(pizza);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		EntityManager em = emf.createEntityManager();
		listPizzas.sort(Comparator.comparing(Pizza::getCode));
		ListUtils.partition(listPizzas, nb).forEach(list -> {
			em.getTransaction().begin();
			list.forEach(em::persist);
			em.getTransaction().commit();

		});
		em.close();
	}

}
