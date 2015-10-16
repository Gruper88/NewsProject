package by.degtev.news;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.News;
import by.degtev.news.services.BaseService;
import by.degtev.news.services.CategoryService;
import by.degtev.news.services.NewsService;
import by.degtev.news.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class NewsServiceTest extends ServiceTest {

    public NewsServiceTest() {
        context = new ClassPathXmlApplicationContext("spring_config_services_test.xml");
        categoryService = context.getBean("categoryService", CategoryService.class);
        newsService = context.getBean("newsService", NewsService.class);
        baseService = context.getBean("baseService", BaseService.class);
        userService = context.getBean("userService", UserService.class);
    }

    @Test
    public void testNewsService() {
        Assert.assertNotNull("Check service: ", newsService);
    }

    @Test
    public void testGet() throws DaoException {
        Integer id = 2;
        String title = "test";
        News news = null;
        try {
            news = (News) baseService.get(News.class, id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test get news is null... ", news);
        Assert.assertEquals("test get news (not equals title)..", news.getTitle(), title);
    }

    @Test
    public void testAdd() throws DaoException {
        News news = new News();
        news.setTitle("test");
        news.setDate(Date.valueOf("2014-12-31"));
        news.setDescription("test");
        news.setNewstext("test");
        try {
            baseService.saveOrUpdate(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add news... null ", news);
        try {
            baseService.deleteByObject(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() throws DaoException {
        Serializable id = null;
        News news = new News();
        news.setTitle("test");
        news.setDate(Date.valueOf("2014-12-31"));
        news.setDescription("test");
        news.setNewstext("test");
        try {
            baseService.saveOrUpdate(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            baseService.deleteByObject(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add news...news null ", news);
    }

    @Test(expected = Exception.class)
    public void testDeleteByNull() {
        Integer id = null;
        News news = null;
        try {
            news = (News) baseService.delete(News.class, id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEdit() {
        String test_actual = "test1";
        News news = new News();
        news.setTitle("test");
        news.setDate(Date.valueOf("2014-12-31"));
        news.setDescription("test");
        news.setNewstext("test");
        try {
            baseService.saveOrUpdate(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            news.setTitle("test1");
            baseService.edit(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        String test_inspected = news.getTitle();
        Assert.assertNotNull("test Edit news by Object... is null ", news);
        Assert.assertEquals("test Edit news by Object...", test_inspected, test_actual);
        try {
            baseService.deleteByObject(news);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllNews() {
        List<News> newsList = null;
        try {
            newsList = newsService.getAllNews();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int size = newsList.size();
        Assert.assertNotNull("test get All News is null..... ", newsList);
        Assert.assertEquals("test get All News Array size: ", 2, size);
    }

    @Test
    public void testAllNewsByDate() {
        List<News> newses = null;
        Date date = Date.valueOf("2014-12-31");
        try {
            newses = newsService.getNewsByDate(date);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int size = newses.size();
        Assert.assertNotNull("test get All News By Date is null..... ", newses);
        Assert.assertEquals("test get All News By Date Array size: ", 2, size);
    }
}
