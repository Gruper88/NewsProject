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
    private static Logger logger = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    /**
     * autowire constructor with SessionFactory
     * @param sessionFactory
     */
    @Autowired
    protected BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * check the current Session
     * @return current Session
     */
    protected Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }

    /**
     * Get an object by ID
     * @param clazz
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public T get(Class<T> clazz, Serializable id) throws DaoException {
        logger.info("Get class by id:" + id);
        T p = null;
        try {
            p = (T) getSession().get(clazz, id);
            getSession().refresh(p);
            logger.info("get class:" + p);
        } catch (HibernateException e) {
            logger.error("Error get " + clazz + " in Dao" + e);
            throw new DaoException(e);
        }
        return p;
    }

    /**
     * Save the object
     * @param p
     * @throws DaoException
     */
    @Override
    public void saveOrUpdate(T p) throws DaoException {
        try {
            getSession().saveOrUpdate(p);
            logger.info("saveOrUpdate(p):" + p);
        } catch (HibernateException e) {
            logger.error("Error save or update Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    /**
     * Delete the object by ID
     * @param clazz
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public T delete(Class<T> clazz, Serializable id) throws DaoException {
        logger.info("Delete class by id:" + id);
        T p = null;
        try {
            p = (T) getSession().get(clazz, id);
            getSession().delete(p);
            logger.info("Delete:" + clazz);
        } catch (HibernateException e) {
            logger.error("Error delete ... in Dao" + e);
            throw new DaoException(e);
        }
        return p;
    }

    /**
     * Delete the object
     * @param p
     * @throws DaoException
     */
    @Override
    public void deleteByObject(T p) throws DaoException {
        try {
            getSession().delete(p);
            logger.info("Delete:" + p);
        } catch (HibernateException e) {
            logger.error("Error delete Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    /**
     * Edit the object
     * @param p
     * @throws DaoException
     */
    @Override
    public void edit(T p) throws DaoException{
        logger.info("Edit class by id:" + p);
        try {
            getSession().update(p);
            logger.info("Edit:" + p);
        } catch (HibernateException e) {
            logger.error("Error Edit Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    /**
     * Get the object identifier
     * @param p
     * @return
     * @throws DaoException
     */
    @Override
    public Serializable getIdentifier(T p) throws DaoException{
        Serializable id = null;
        try {
            id = getSession().getIdentifier(p);
        }catch (HibernateException e){
            logger.error("Error was thrown in Dao", e);
            throw new DaoException(e);
        }
        return id;
    }

}
