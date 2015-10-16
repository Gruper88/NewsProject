package by.degtev.news.daoTest;


import by.degtev.news.dao.BaseDao;
import by.degtev.news.dao.CategoryDao;
import by.degtev.news.dao.NewsDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.News;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.List;

public class NewsDaoTest extends DaoTest {
    private static Transaction transaction = null;
    private static ApplicationContext context;
    private static CategoryDao categoryDao;
    private static BaseDao baseDao;
    private static NewsDao newsDao;
    private static SessionFactory sessionFactory;


    public NewsDaoTest() {
        context = new ClassPathXmlApplicationContext("spring_config_dao_test.xml");
        categoryDao = context.getBean("categoryDao", CategoryDao.class);
        newsDao = context.getBean("newsDao", NewsDao.class);
        baseDao = context.getBean("baseDao", BaseDao.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @Test
    public void testDao() {
        Assert.assertNotNull("Check dao: ", newsDao);
    }

    /**
     * extraction of the object class News by id.
     */
    @Test
    public void testGet() throws DaoException {
        Integer id = 2;
        String title = "test";
        News news = (News) baseDao.get(News.class, id);
        Assert.assertNotNull("test get news is null... ", news);
        Assert.assertEquals("test get news (not equals title)..", news.getTitle(), title);
    }

    /**
     * extraction of the object class News by id(null).
     */
    @Test(expected = Exception.class)
    public void testGetNull() throws DaoException {
        News news = null;
        try {
            news = (News) baseDao.get(News.class, null);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding an object class News = null.
     */
    @Test(expected = Exception.class)
    public void testAddNull() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config_dao_test.xml");
        BaseDao baseDao = context.getBean("baseDao", BaseDao.class);
        News news = null;
        try {
            baseDao.saveOrUpdate(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add news... null ", news);
    }

    /**
     * delete the object class News by null.
     */
    @Test(expected = Exception.class)
    public void testDeleteByNull() {
        Integer id = null;
        News news = null;
        try {
            news = (News) baseDao.delete(News.class, id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Editing object class News = null.
     */
    @Test(expected = Exception.class)
    public void testEditNull() {
        News news = null;
        try {
            baseDao.edit(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * extraction of the all objects class News
     */
    @Test
    public void testGetAllNews() throws DaoException {
        List<News> newses = null;
        newses = newsDao.getAllNews();
        int size = newses.size();
        Assert.assertNotNull("test get All News is null..... ", newses);
        Assert.assertEquals("test get All News Array size: ", 2, size);
    }

    /**
     * extraction of the objects class News by Date.
     */
    @Test
    public void testGetAllByDate() throws DaoException {
        List<News> newses;
        Date date = Date.valueOf("2014-12-31");
        newses = newsDao.getNewsByDate(date);
        int size = newses.size();
        Assert.assertNotNull("test get News By Date is null..... ", newses);
        Assert.assertEquals("test get News By Date Array size: ", 2, size);
    }

}
