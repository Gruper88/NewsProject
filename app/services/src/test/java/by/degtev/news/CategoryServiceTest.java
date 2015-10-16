package by.degtev.news;


import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.Category;
import by.degtev.news.services.BaseService;
import by.degtev.news.services.CategoryService;
import by.degtev.news.services.NewsService;
import by.degtev.news.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CategoryServiceTest extends ServiceTest {

    public CategoryServiceTest() {
        context = new ClassPathXmlApplicationContext("spring_config_services_test.xml");
        categoryService = context.getBean("categoryService", CategoryService.class);
        newsService = context.getBean("newsService", NewsService.class);
        baseService = context.getBean("baseService", BaseService.class);
        userService = context.getBean("userService", UserService.class);
    }

    @Test
    public void testNewsService() {
        Assert.assertNotNull("Check categoryService: ", categoryService);
    }

    @Test
    public void testGetCategoryByCategoryName() throws DaoException {
        String category_name = "test2";
        Category category = null;
        category = categoryService.getCategoryByName(category_name);
        Assert.assertNotNull("test get Category by  name is null..... ", category);
        Assert.assertEquals("test get Category by  name: ", category_name, category.getCategory());
    }

    @Test
    public void testAddCategory() throws DaoException {
        Category category = new Category();
        category.setCategory("test");
        try {
            baseService.saveOrUpdate(category);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add news... null ", category);
        try {
            baseService.deleteByObject(category);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
