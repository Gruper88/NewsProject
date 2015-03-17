package by.degtev.news;

import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;
import by.degtev.news.services.BaseService;
import by.degtev.news.services.CategoryService;
import by.degtev.news.services.NewsService;
import by.degtev.news.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for UserServices.
 */
public class UserServiceTest extends ServiceTest{

    public UserServiceTest(){
        context = new ClassPathXmlApplicationContext("spring_config_services_test.xml");
        categoryService = context.getBean("categoryService", CategoryService.class);
        newsService = context.getBean("newsService", NewsService.class);
        baseService = context.getBean("baseService", BaseService.class);
        userService = context.getBean("userService", UserService.class);
    }

    /**
     * creating an object UserDao.
     */
    @Test
    public void testUserService(){
        Assert.assertNotNull("Check userService: ", userService);
    }

    /**
     * comparison username and password in class UserData.
     */
    @Test
    public  void testCheckPassword() throws DaoException {
        String password= "test";
        String email = "test2";
        String userForSession = null;
        try {
            userForSession = userService.checkPassword(email,password);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("Check user don't work " + userForSession);
        Assert.assertEquals("test get user (not equals user email)..", email, userForSession);

    }

    /**
     *
     */
    @Test
    public  void testUserAdd() throws DaoException {
        User user = new User();
        user.setEmail("test");
        user.setPassword("test");
        try {
            baseService.saveOrUpdate(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add User... null ", user);
        baseService.deleteByObject(user);
    }


}
