package by.degtev.news.dao;

import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements IUserDao {
    final static Logger LOGGER = Logger.getLogger(UserDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    protected UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //TODO: To make a separate class !!!
    private Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            LOGGER.debug("Unable to get Hibernate session", e);
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }

    @Override
    public String checkPassword(String email, String password) throws DaoException {
        String userForSession = null;
        User user = null;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password", password));
            List results = criteria.list();
            if (results.size() > 0) {
                userForSession = email;
            }
            LOGGER.info("Check password:");
        } catch (HibernateException e) {
            LOGGER.error("Error check password " + user + " in Dao" + e);
            throw new DaoException(e);
        }
        return userForSession;
    }

    @Override
    public String checkPasswordHQL(String email, String password) throws DaoException {
        String userForSession = null;
        User user = null;
        String hql = "FROM User U WHERE U.email=:email AND U.password=:password";
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            List results = query.list();
            if (results.size() > 0) {
                userForSession = email;
            }
            LOGGER.info("Check password:");
        } catch (HibernateException e) {
            LOGGER.error("Error check password: " + user + " in Dao" + e);
            throw new DaoException(e);
        }
        return userForSession;
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> userList = new ArrayList<User>();
        try {
            userList = getSession().createCriteria(User.class).list();
            LOGGER.info("Get user list:" + userList);
        } catch (HibernateException e) {
            LOGGER.error("Error get user list: " + userList + " in Dao" + e);
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        User user = null;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            user = (User) criteria.uniqueResult();
            LOGGER.info("Get user by email: " + user);
        } catch (HibernateException e) {
            LOGGER.error("Error get user by email: " + email + " in Dao" + e);
            throw new DaoException(e);
        }
        return user;
    }
}
