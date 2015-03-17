package by.degtev.news.services;


import by.degtev.news.dao.exceptions.DaoException;

import java.io.Serializable;

public interface IBaseService<T> {

    T get(Class<T> clazz, Serializable id) throws DaoException;

    void saveOrUpdate(T p) throws DaoException;

    void deleteByObject(T p) throws DaoException;

    public T delete(Class<T> clazz, Serializable id) throws DaoException;

    void edit(T p) throws DaoException;

    public Serializable getIdentifier(T p) throws DaoException;

}
