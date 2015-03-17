package by.degtev.news.daoTest;


import by.degtev.news.dao.BaseDao;
import by.degtev.news.dao.CategoryDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CategoryDaoTest extends DaoTest {
    private static ApplicationContext context;
    private static CategoryDao categoryDao;
    private static BaseDao baseDao;
    private static SessionFactory sessionFactory;


    public CategoryDaoTest(){
        context = new ClassPathXmlApplicationContext("spring_config_dao_test.xml");
        categoryDao = context.getBean("categoryDao", CategoryDao.class);
        baseDao = context.getBean("baseDao", BaseDao.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }


    /**
     * check the current Session
     * @return current Session
     */
    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
        }
        if (!(session != null && session.isOpen())) session = sessionFactory.openSession();
        return session;
    }

    /**
     * creating an object CategoryDao.
     */
    @Test
    public void testCategoryDao(){
        Assert.assertNotNull("Check dao: ", categoryDao);
    }

    /**
     * extraction all Categories.
     */
    @Test
    public void testGetAllCategory() throws DaoException {
        List<Category> categoryList;
        categoryList = categoryDao.getAllCategories();
        int size = categoryList.size();
        Assert.assertNotNull("test get All Category is null..... ", categoryList);
        Assert.assertEquals("test get All Category Array size: ", 2, size);
    }

    /**
     * extraction  Category By Category Name.
     */
    @Test
    public void testGetCategoryByCategoryName() throws DaoException {
        String category_name = "test2";
        Category category = null;
        category = categoryDao.getCategoryByName(category_name);
        Assert.assertNotNull("test get Category by  name is null..... ", category);
        Assert.assertEquals("test get Category by  name: ", category_name, category.getCategory());
    }

    /**
     * Get category be id
     */
    @Test
    public void testGetCategoryById() throws DaoException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config_dao_test.xml");
        Integer id = 2;
        String category_name = "test2";
        Category category;
        category = (Category) baseDao.get(Category.class, id);
        Assert.assertNotNull("test get category is null... ", category);
        Assert.assertEquals("test get category (not equals category name)..", category_name, category.getCategory());
    }

    /**
    * Adding an object class Category.
    */
    //@Test
    public void testAddCategory() throws DaoException {
        Session session = getSession();
        Category category = null;
        category = createObject(category);
        try {
            baseDao.saveOrUpdate(category);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("Add category - null! ", category);
        try {
            baseDao.deleteByObject(category);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get gategory be news
     */
    @Test
    public void testGetCategoryByNews() throws DaoException {
        List<Category> categoryList;
        news1 = (News) baseDao.get(News.class, 2);
        categoryList = (List) categoryDao.getCategoryByNews(news1);
        int size = categoryList.size();
        Assert.assertNotNull("Category by  news is null!", categoryList);
        Assert.assertEquals("Count categories failed!", 1, size);

    }
}
