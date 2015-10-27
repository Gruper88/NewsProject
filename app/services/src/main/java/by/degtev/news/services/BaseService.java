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
    final static Logger LOGGER = Logger.getLogger(BaseService.class);

    private IBaseDao<T> baseDao;

    @Autowired
    public BaseService(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T get(Class<T> obj, Serializable id) throws DaoException {
        if (id == null) return (T) new DaoException();
        T pojo = null;
        try {
            pojo = (T) baseDao.get(obj, id);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return pojo;
    }

    @Override
    public void saveOrUpdate(Object pojo) throws DaoException {
        if (pojo == null) new DaoException();
        try {
            baseDao.saveOrUpdate((T) pojo);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteByObject(T pojo) throws DaoException {
        if (pojo == null) new DaoException();
        try {
            baseDao.deleteByObject(pojo);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public T delete(Class obj, Serializable id) throws DaoException {
        if (id == null) return (T) new DaoException();
        T pojo = null;
        try {
            pojo = (T) baseDao.delete(obj, id);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return pojo;
    }

    @Override
    public void edit(Object pojo) throws DaoException {
        if (pojo == null) new DaoException();
        try {
            baseDao.edit((T) pojo);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Serializable getIdentifier(Object pojo) throws DaoException {
        Serializable id = null;
        try {
            pojo = (T) baseDao.getIdentifier((T) pojo);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return id;
    }
}
