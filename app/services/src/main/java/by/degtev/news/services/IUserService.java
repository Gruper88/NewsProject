package by.degtev.news.services;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;

public interface IUserService {

    public String checkPassword(String email, String password) throws DaoException;

    public User getUserByEmail(String email) throws DaoException;

}
