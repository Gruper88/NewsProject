package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;

import java.util.List;

public interface IUserDao {

    public String checkPassword(String email, String password) throws DaoException;

    public String checkPasswordHQL(String email, String password) throws DaoException;

    public List<User> getAllUsers() throws DaoException;

    public User getUserByEmail(String email) throws DaoException;

}
