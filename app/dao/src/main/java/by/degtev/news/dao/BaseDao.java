package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class BaseDao<T> implements IBaseDao<T> {
    final static Logger LOGGER = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    protected BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //TODO: To make a separate class !!!
    protected Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }

    @Override
    public T get(Class<T> obj, Serializable id) throws DaoException {
        LOGGER.info("Get class by id:" + id);
        T pojo = null;
        try {
            pojo = (T) getSession().get(obj, id);
            getSession().refresh(pojo);
            LOGGER.info("get class:" + pojo);
        } catch (HibernateException e) {
            LOGGER.error("Error get " + obj + " in Dao" + e);
            throw new DaoException(e);
        }
        return pojo;
    }

    @Override
    public void saveOrUpdate(T pojo) throws DaoException {
        try {
            getSession().saveOrUpdate(pojo);
            LOGGER.info("saveOrUpdate(p):" + pojo);
        } catch (HibernateException e) {
            LOGGER.error("Error save or update Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public T delete(Class<T> obj, Serializable id) throws DaoException {
        LOGGER.info("Delete class by id:" + id);
        T pojo = null;
        try {
            pojo = (T) getSession().get(obj, id);
            getSession().delete(pojo);
            LOGGER.info("Delete:" + obj);
        } catch (HibernateException e) {
            LOGGER.error("Error delete ... in Dao" + e);
            throw new DaoException(e);
        }
        return pojo;
    }

    @Override
    public void deleteByObject(T pojo) throws DaoException {
        try {
            getSession().delete(pojo);
            LOGGER.info("Delete:" + pojo);
        } catch (HibernateException e) {
            LOGGER.error("Error delete Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public void edit(T pojo) throws DaoException {
        LOGGER.info("Edit class by id:" + pojo);
        try {
            getSession().update(pojo);
            LOGGER.info("Edit:" + pojo);
        } catch (HibernateException e) {
            LOGGER.error("Error Edit Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public Serializable getIdentifier(T pojo) throws DaoException {
        Serializable id = null;
        try {
            id = getSession().getIdentifier(pojo);
        } catch (HibernateException e) {
            LOGGER.error("Error was thrown in Dao", e);
            throw new DaoException(e);
        }
        return id;
    }

}
