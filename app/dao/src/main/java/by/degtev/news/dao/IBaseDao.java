package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;

import java.io.Serializable;

public interface IBaseDao<T> {

    T get(Class<T> obj, Serializable id) throws DaoException;

    void saveOrUpdate(T pojo) throws DaoException;

    void deleteByObject(T pojo) throws DaoException;

    public T delete(Class<T> obj, Serializable id) throws DaoException;

    void edit(T pojo) throws DaoException;

    public Serializable getIdentifier(T pojo) throws DaoException;

}
