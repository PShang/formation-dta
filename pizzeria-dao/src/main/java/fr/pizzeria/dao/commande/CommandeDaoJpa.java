package fr.pizzeria.dao.commande;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.StatutCommande;

public class CommandeDaoJpa implements ICommandeDao {

	private EntityManagerFactory emf;

	public CommandeDaoJpa(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Override
	public void saveCommande(Commande newCommande) throws DaoException {
		newCommande.setDateCommande(Calendar.getInstance());
		newCommande.setStatut(StatutCommande.NON_TRAITE);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(newCommande.getClient());
			em.persist(newCommande);
			newCommande.setNumeroCommande("COMMANDE_" + newCommande.getId());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Commande> listerCommande(Integer idClient) throws DaoException {
		List<Commande> commandes = new ArrayList<>();
		EntityManager em = emf.createEntityManager();

		try {
			commandes = em.createQuery("select c from Commande c where c.client.id=:idClient", Commande.class)
						.setParameter("idClient", idClient)
						.getResultList();
		} finally {
			em.close();
		}
		return commandes;
	}

}
