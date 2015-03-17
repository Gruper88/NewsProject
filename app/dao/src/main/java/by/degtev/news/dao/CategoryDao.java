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
    private static Logger logger = Logger.getLogger(CategoryDao.class);
    private SessionFactory sessionFactory;

    /**
     * autowire constructor with SessionFactory
     * @param sessionFactory
     */
    @Autowired
    private CategoryDao(SessionFactory sessionFactory) {
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
     * Get List all categories
     * @return ArrayList
     */
    @Override
    public List<Category> getAllCategories() throws DaoException {
        List<Category> categories = new ArrayList<Category>();
        logger.info("Get class all objects:");
        try {
            Query query = getSession().createQuery("FROM Category");
            categories = query.list();
            for (Category category: categories)getSession().refresh(category);
            logger.info("get clazz:" + categories);
        } catch (HibernateException e) {
            logger.error("Error get " + categories + " in Dao" + e);
            throw new DaoException(e);
        }
        return categories;
    }

    /**
     *Get categories List by news
     * @param
     * @return ArrayList.
     */
    @Override
    public List<Category> getCategoryByNews(News p) throws DaoException {
        List<Category> categories = new ArrayList<Category>();
        logger.info("Get Category By News objects:");
        try {
            Set set =  p.getCategories();
            categories.addAll(set);
            logger.info("get clazz:" + categories);
        } catch (HibernateException e) {
            logger.error("Error get " + categories + " in Dao" + e);
            throw new DaoException(e);
        }
        return categories;
    }

    /**
     *Get categories List by name
     * @param category_name
     * @return Category.
     */
    @Override
    public Category getCategoryByName(String category_name) throws DaoException{
        Category category = null;
        try {
            Criteria criteria = getSession().createCriteria(Category.class);
            criteria.add(Restrictions.eq("category", category_name));
            category = (Category) criteria.uniqueResult();
            getSession().refresh(category);
        } catch (HibernateException e) {
            logger.error("Error get " + category_name + " in Dao" + e);
            throw new DaoException(e);
        }
        return category;
    }
}
