package fr.pizzeria.admin.metier;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaService extends AbstractService<Pizza>{
	
	@Resource private ServletContext srv;

	@PersistenceContext(unitName="pizzeria-admin-web") private EntityManager em;

	public Pizza findOnePizza(String code) throws DaoException {
		try {
			return em.createQuery("select p from Pizza p where code=:code", Pizza.class).setParameter("code", code).getSingleResult();
		} catch(NoResultException e) {
			throw new DaoException(e);
		}
	}

	public void updatePizza(String code, Pizza pizzaAvecId) throws DaoException {
		findOnePizza(code); // vérifie qu'un pizza est présente
		em.merge(pizzaAvecId);
	}

	public void savePizza(Pizza pizzaSansId) throws DaoException {
		em.persist(pizzaSansId);
	}

	public List<Pizza> findAllPizzas() throws DaoException {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

	public void deletePizza(String code) throws DaoException {
		em.remove(findOnePizza(code));
	}
	
	@PostConstruct
	public void init() throws IOException {
		System.out.println("ddd"  +srv);
			}
	
}
