package fr.pizzeria.admin.metier;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.exception.DaoException;

public abstract class AbstractService<T> {

    protected Class<T> entityClass;

    @PersistenceContext protected EntityManager em;

    public AbstractService() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
    
    public List<T> findAll() throws DaoException {
		return em.createQuery(String.format("select p from %s p", entityClass.getName()), entityClass).getResultList();
	}

    public T save(T t) {
        this.em.persist(t);
        return t;
    }

}
