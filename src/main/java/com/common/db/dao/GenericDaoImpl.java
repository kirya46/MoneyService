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

    private Session getCuurentSesstion(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    @Transactional
    public void persist(E persistentEntity) {
        this.getCuurentSesstion().persist(persistentEntity);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public PK save(E newInstance) {
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        final PK save = (PK) cuurentSesstion.save(newInstance);
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();
        return save;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(PK id) {
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        final E e = (E) cuurentSesstion.get(getEntityClass(), id);
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        final List<E> list = cuurentSesstion.createCriteria(getEntityClass()).list();
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();
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
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        cuurentSesstion.update(transientObject);
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();
    }

    @Override
    public void saveOrUpdate(E transientObject) {
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        cuurentSesstion.saveOrUpdate(transientObject);
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();

    }

    @Override
    public void delete(E persistentObject) {
        final Session cuurentSesstion = this.getCuurentSesstion();
        cuurentSesstion.getTransaction().begin();
        cuurentSesstion.delete(persistentObject);
        cuurentSesstion.getTransaction().commit();
        cuurentSesstion.close();
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
