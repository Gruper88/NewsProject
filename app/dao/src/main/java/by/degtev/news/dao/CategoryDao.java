package by.degtev.news.dao;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDao implements ICategoryDao {
    final static Logger LOGGER = Logger.getLogger(CategoryDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    private CategoryDao(SessionFactory sessionFactory) {
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
    public List<Category> getAllCategories() throws DaoException {
        List<Category> categories = new ArrayList<Category>();
        try {
            Query query = getSession().createQuery("FROM Category");
            categories = query.list();
            for (Category category : categories) getSession().refresh(category);
            LOGGER.info("Get categories list" + categories);
        } catch (HibernateException e) {
            LOGGER.error("Error get categories list " + categories + " in Dao" + e);
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public List<Category> getCategoryByNews(News news) throws DaoException {
        List<Category> categories = new ArrayList<Category>();
        try {
            Set set = news.getCategories();
            categories.addAll(set);
            LOGGER.info("Get the news categories" + categories);
        } catch (HibernateException e) {
            LOGGER.error("Error get the news categories " + categories + " in Dao" + e);
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String category_name) throws DaoException {
        Category category = null;
        try {
            Criteria criteria = getSession().createCriteria(Category.class);
            criteria.add(Restrictions.eq("category", category_name));
            category = (Category) criteria.uniqueResult();
            getSession().refresh(category);
        } catch (HibernateException e) {
            LOGGER.error("Error get  category by name" + category_name + " in Dao" + e);
            throw new DaoException(e);
        }
        return category;
    }
}
