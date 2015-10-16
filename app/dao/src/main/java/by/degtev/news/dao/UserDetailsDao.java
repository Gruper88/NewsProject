package by.degtev.news.dao;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDao implements IUserDetailsDao {
    private static Logger logger = Logger.getLogger(UserDetailsDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    protected UserDetailsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //TODO: To make a separate class !!!
    private Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            logger.debug("Unable to get Hibernate session", e);
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }
}
