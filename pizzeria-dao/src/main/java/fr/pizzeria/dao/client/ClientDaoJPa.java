package fr.pizzeria.dao.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public class ClientDaoJPa implements IClientDao {

	private EntityManagerFactory emf;

	public ClientDaoJPa(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Override
	public void saveClient(Client client) throws DaoException {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			// TODO vérification email
			String motDePasseHash = masquerMotDePasse(client.getMotDePasse());
			client.setMotDePasse(motDePasseHash);
			em.persist(client);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	private String masquerMotDePasse(String motDePasse) {
		return DigestUtils.md5Hex(motDePasse);
	}

	@Override
	public Client login(String email, String motDePasse) throws DaoException {
		Client client = null;
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Client> query = em.createQuery("select c from Client c where c.email=:email", Client.class);
			query.setParameter("email", email);
			client = query.getSingleResult();
			
			if (!masquerMotDePasse(motDePasse).equals(client.getMotDePasse())) {
				throw new DaoException("Mot de passe incorrect");
			}
		} catch (NoResultException e) {
			throw new DaoException("Email non trouvé", e);
		} finally {
			em.close();
		}
		
		return client;
	}

}
