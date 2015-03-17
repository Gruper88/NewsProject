package by.degtev.news.daoTest;


import by.degtev.news.dao.BaseDao;
import by.degtev.news.dao.UserDao;
import by.degtev.news.dao.exceptions.DaoException;
import by.degtev.news.pojos.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest extends DaoTest{
    private static Transaction transaction = null;
    private static ApplicationContext context;
    private static BaseDao baseDao;
    private static UserDao userDao;
    private static SessionFactory sessionFactory;


    public UserDaoTest(){
        context = new ClassPathXmlApplicationContext("spring_config_dao_test.xml");
        baseDao = context.getBean("baseDao", BaseDao.class);
        userDao = context.getBean("userDao", UserDao.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    /**
     * creating an object UserDao.
     */
    @Test
    public void testUserDao(){
        Assert.assertNotNull("Check dao: ", userDao);
    }


    /**
     * extraction all User.
     */
    //@Test
    public void testGetAllUser() throws DaoException {

    }

    /**
     * CheckPassword
     * @throws by.degtev.news.dao.exceptions.DaoException
     */
    @Test
    public void testCheckPassword()throws DaoException{
        String password= "test";
        String email = "test2";
        String userForSession = null;
        userForSession = userDao.checkPassword(email,password);
        Assert.assertNotNull("Check user don't work " + userForSession);
        Assert.assertEquals("test get user (not equals user email)..", email, userForSession);
    }

    /**
     * extraction  User By Id.
     */
    @Test
    public void testGetUserById() throws DaoException {
        Integer id = 2;
        String user_email = "test2";
        User user = null;
        user = (User) baseDao.get(User.class, id);
        Assert.assertNotNull("test get user is null... ", user);
        Assert.assertEquals("test get user (not equals user email)..", user_email, user.getEmail());
    }

    /**
     * Adding an object class User.
     */
    //@Test
    public void testAddUser() throws DaoException {
        User user = null;
        try {
            user = createObject(user);
            baseDao.saveOrUpdate(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("test add User... null ", user);
        baseDao.deleteByObject(user);
    }

    /**
     * extraction  User By  Email.
     */
    @Test
    public void testGetUserByEmail() throws DaoException {
        String user_email = "test2";
        User user = null;
        user = userDao.getUserByEmail(user_email);
        Assert.assertNotNull("test get User by  user_email is null..... ", user);
        Assert.assertEquals("test get User by  user_email: ", user_email, user.getEmail());
    }

}
