package by.degtev.news.services;


import by.degtev.news.dao.exceptions.DaoException;

import java.io.Serializable;

public interface IBaseService<T> {

    T get(Class<T> obj, Serializable id) throws DaoException;

    void saveOrUpdate(T pojo) throws DaoException;

    void deleteByObject(T pojo) throws DaoException;

    public T delete(Class<T> obj, Serializable id) throws DaoException;

    void edit(T pojo) throws DaoException;

    public Serializable getIdentifier(T pojo) throws DaoException;

}
