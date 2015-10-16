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
    final static Logger LOGGER = Logger.getLogger(NewsDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    protected NewsDao(SessionFactory sessionFactory) {
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
    public List<News> getAllNews() throws DaoException {
        List<News> newses = new ArrayList<News>();
        try {
            newses = getSession().createCriteria(News.class).list();
            for (News news : newses) getSession().refresh(news);
            LOGGER.info("Get news list " + newses);
        } catch (HibernateException e) {
            LOGGER.error("Error get  news list " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    @Override
    public List<News> getNewsByDate(Date date) throws DaoException {
        Date date_in = date;
        List<News> newses = new ArrayList<News>();
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.add(Restrictions.eq("date", date));
            newses = (List<News>) criteria.list();
            LOGGER.info("Get news by date" + newses);
        } catch (HibernateException e) {
            LOGGER.error("Error get news by date " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    /**
     * Get List sorting by date
     *
     * @return ArrayList,
     */
    @Override
    public List<News> getSorting(String sorting) throws DaoException {
        List<News> newses = new ArrayList<News>();
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.addOrder(Order.asc(sorting));
            newses = (List<News>) criteria.list();
            for (News news : newses) getSession().refresh(news);
            LOGGER.info("Get sorting news" + newses);
        } catch (HibernateException e) {
            LOGGER.error("Error get sorting news" + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

    @Override
    public List<News> getPagination(int start, int count) throws DaoException {
        List<News> newses = new ArrayList<News>();
        try {
            Criteria criteria = getSession().createCriteria(News.class);
            criteria.setFirstResult(start);
            criteria.setMaxResults(count);
            newses = (List<News>) criteria.list();
            for (News news : newses) getSession().refresh(news);
            LOGGER.info("Get pagination news" + newses);
        } catch (HibernateException e) {
            LOGGER.error("Error get pagination news " + newses + " in Dao" + e);
            throw new DaoException(e);
        }
        return newses;
    }

}
