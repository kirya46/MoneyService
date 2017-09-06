package com.common.db.dao;

import com.common.db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Kirill Stoianov on 06/09/17.
 */

@Transactional
public abstract class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK> {

    private Session currentSession;
    public GenericDaoImpl() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    @Transactional
    public void persist(E persistentEntity) {
        this.currentSession.persist(persistentEntity);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public PK save(E newInstance) {
        this.currentSession.getTransaction().begin();
        final PK save = (PK) this.currentSession.save(newInstance);
        this.currentSession.getTransaction().commit();
        return save;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(PK id) {
        this.currentSession.getTransaction().begin();
        final E e = (E) this.currentSession.get(getEntityClass(), id);
        this.currentSession.getTransaction().commit();
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        this.currentSession.getTransaction().begin();
        final List<E> list = this.currentSession.createCriteria(getEntityClass()).list();
        this.currentSession.getTransaction().commit();
        return list;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAllByProperty(String propertyName, Object value) {

        throw new UnsupportedOperationException();
    }

    public List<E> findByExample(E object) {
        throw new UnsupportedOperationException();
    }

    public List<E> findByExample(E object, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(E transientObject) {
        this.currentSession.getTransaction().begin();
        this.currentSession.update(transientObject);
        this.currentSession.getTransaction().commit();
    }

    @Override
    public void saveOrUpdate(E transientObject) {
        this.currentSession.getTransaction().begin();
        this.currentSession.saveOrUpdate(transientObject);
        this.currentSession.getTransaction().commit();
    }

    @Override
    public void delete(E persistentObject) {
        this.currentSession.getTransaction().begin();
        this.currentSession.delete(persistentObject);
        this.currentSession.getTransaction().commit();
    }

    @Override
    public void delete(PK id) {
        final E entity = this.findById(id);
        if (entity != null) this.delete(entity);
    }

    @Override
    public boolean isExists(PK id) {
        return this.findById(id) != null;
    }


    protected abstract Class<E> getEntityClass();

    private DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }
}
