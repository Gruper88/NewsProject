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
    private static Logger logger = Logger.getLogger(UserDao.class);
    private SessionFactory sessionFactory;

    /**
     * autowire constructor with SessionFactory
     * @param sessionFactory
     */
    @Autowired
    protected UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Check the current Session
     * @return current Session
     */
    private Session getSession(){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            logger.debug("Could not retrieve pre-bound Hibernate session", e);
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }

    /**
     * comparison username and password in class UserData.
     * @param email
     * @param password
     * @return user for session.
     */
    @Override
    public String checkPassword(String email, String password) throws DaoException {
        String userForSession = null;
        User user = null;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password",password));
            List results = criteria.list();
            if (results.size()>0){
                userForSession = email;
            }
            logger.info("checkPassword:");
        } catch (HibernateException e) {
            logger.error("Error checkPassword " + user + " in Dao" + e);
            throw new DaoException(e);
        }
        return userForSession;
    }

    /**
     * HQL query
     * @param email
     * @param password
     * @return
     * @throws DaoException
     */
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
            if (results.size()>0){
                userForSession = email;
            }
            logger.info("checkPassword:");
        } catch (HibernateException e) {
            logger.error("Error checkPassword " + user + " in Dao" + e);
            throw new DaoException(e);
        }
        return userForSession;
    }

    /**
     * Get list all Users
     * @return
     * @throws DaoException
     */
    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> userList = new ArrayList<User>();
        logger.info("Get class all objects:");
        try {
            userList = getSession().createCriteria(User.class).list();
            logger.info("get clazz:" + userList);
        } catch (HibernateException e) {
            logger.error("Error get " + userList + " in Dao" + e);
            throw new DaoException(e);
        }
        return userList;
    }

    /**
     * Get User By E-mai
     * @param email
     * @return
     * @throws DaoException
     */
    @Override
    public User getUserByEmail(String email) throws DaoException {
        User user = null;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            user = (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get " + email + " in Dao" + e);
            throw new DaoException(e);
        }
        return user;
    }
}
