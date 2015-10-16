package by.degtev.news.services;


import by.degtev.news.dao.IUserDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService implements IUserService {
    final static Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Override
    public String checkPassword(String email, String password) throws DaoException {
        if (email == null || password == null) new DaoException();
        String userEmail;
        try {
            userEmail = userDao.checkPassword(email, password);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return userEmail;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        if (email == null) new DaoException();
        User user;
        try {
            user = userDao.getUserByEmail(email);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return user;
    }
}
