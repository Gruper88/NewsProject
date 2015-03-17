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

    /**
     * autowire constructor with SessionFactory
     * @param sessionFactory
     */
    @Autowired
    protected UserDetailsDao(SessionFactory sessionFactory) {
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

}
