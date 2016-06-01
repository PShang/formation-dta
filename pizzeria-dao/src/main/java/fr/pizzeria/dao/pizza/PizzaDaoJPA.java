package fr.pizzeria.dao.pizza;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoJPA implements IPizzaDao {
	private static final Logger LOG = Logger.getLogger(PizzaDaoJPA.class.toString());


	private EntityManagerFactory emf;

	@Autowired
	public PizzaDaoJPA(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		LOG.log(Level.INFO, "Création du bean PizzaDaoJPA");
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		EntityManager em = emf.createEntityManager();
		List<Pizza> listPizzas = em.createQuery("select p from Pizza p", Pizza.class).getResultList();
		em.close();
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
		try{
		em.getTransaction().begin();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code=:codePizza", Pizza.class);
		query.setParameter("codePizza", codePizza);
		Pizza pizza = query.getSingleResult();
		pizza.setCode(updatePizza.getCode());
		pizza.setNom(updatePizza.getNom());
		pizza.setPrix(updatePizza.getPrix());
		pizza.setCategorie(updatePizza.getCategorie());
		em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		EntityManager em = emf.createEntityManager();
		try{
		em.getTransaction().begin();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code=:codePizza", Pizza.class);
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

	@Override
	public Pizza doInTransaction(TransactionStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

}
