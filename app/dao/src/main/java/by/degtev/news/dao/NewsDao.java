package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.News;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDao implements INewsDao {
    private static Logger logger = Logger.getLogger(NewsDao.class);
    private SessionFactory sessionFactory;

    /**
     * autowire constructor with SessionFactory
     * @param sessionFactory
     */
    @Autowired
    protected NewsDao(SessionFactory sessionFactory) {
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
     * Get List all news
     * @return ArrayList
     */
    @Override
    public List<News> getAllNews() throws DaoException {
        List<News> newses = new ArrayList<News>();
        logger.info("Get class all objects:");
        try {
            newses = getSession().createCriteria(News.class).list();
            for (News news: newses)getSession().refresh(news);
            logger.info("get clazz:" + newses);
        } catch (HibernateException e) {
            logger.error("Error get " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     * Get List by date
     * @param date
     * @return ArrayList,
     */
    @Override
    public List<News> getNewsByDate(Date date) throws DaoException {
        Date date_in = date;
        List<News> newses = new ArrayList<News>();
        logger.info("Get class all objects By Date:");
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.add(Restrictions.eq("date", date));
            newses = (List<News>) criteria.list();
            logger.info("get class By Date:" + newses);
        } catch (HibernateException e) {
            logger.error("Error get By Date " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     * Get List sorting by date
     * @return ArrayList,
     */
    @Override
    public List<News> getSorting(String sorting) throws DaoException {
        List<News> newses = new ArrayList<News>();
        logger.info("Get class all objects By Date:");
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.addOrder(Order.asc(sorting));
            newses = (List<News>) criteria.list();
            for (News news: newses)getSession().refresh(news);
            logger.info("get class By Date:" + newses);
        } catch (HibernateException e) {
            logger.error("Error get By Date " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     * Get List Pagination
     * @param start
     * @param count
     * @return
     * @throws DaoException
     */
    @Override
    public List<News> getPagination(int start, int count) throws DaoException {
        List<News> newses = new ArrayList<News>();
        logger.info("Get class all objects By Date:");
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.setFirstResult(start);
            criteria.setMaxResults(count);
            newses = (List<News>) criteria.list();
            for (News news: newses)getSession().refresh(news);
            logger.info("get class By Date:" + newses);
        } catch (HibernateException e) {
            logger.error("Error get By Date " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

}
