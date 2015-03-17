package by.degtev.news.services;

import by.degtev.news.dao.IBaseDao;
import by.degtev.news.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
public class BaseService<T> implements IBaseService<T> {
    private static Logger log = Logger.getLogger(BaseService.class);

    private IBaseDao<T> baseDao;

    public BaseService() {
    }

    @Autowired
    public BaseService(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    /**
     *
     * @param clazz
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T get(Class<T> clazz, Serializable id) throws DaoException {
        if(id == null) return (T) new DaoException();
        T p = null;
        try {
            p = (T) baseDao.get(clazz, id);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return p;
    }

    /**
     *
     * @param p
     * @throws DaoException
     */
    @Override
    public void saveOrUpdate(Object p) throws DaoException {
        if(p == null)  new DaoException();
        try {
            baseDao.saveOrUpdate((T) p);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    /**
     *
     * @param p
     * @throws DaoException
     */
    @Override
    public void deleteByObject(T p) throws DaoException {
        if(p == null) new DaoException();
        try {
            baseDao.deleteByObject(p);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    /**
     *
     * @param clazz
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public T delete(Class clazz, Serializable id) throws DaoException {
        if(id == null) return (T) new DaoException();
        T p = null;
        try {
            p = (T) baseDao.delete(clazz, id);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return p;
    }

    /**
     *
     * @param p
     * @throws DaoException
     */
    @Override
    public void edit(Object p) throws DaoException {
        if(p == null)  new DaoException();
        try {
            baseDao.edit((T) p);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    /**
     *
     * @param p
     * @return
     * @throws DaoException
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Serializable getIdentifier(Object p) throws DaoException {
        Serializable id = null;
        try {
            p = (T) baseDao.getIdentifier((T) p);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return id;
    }

}
