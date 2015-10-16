package by.degtev.news.daoTest;


import by.degtev.news.dao.BaseDao;
import by.degtev.news.dao.UserDetailsDao;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDetailDaoTest extends DaoTest {
    private static ApplicationContext context;
    private static BaseDao baseDao;
    private static UserDetailsDao userDetailsDao;
    private static SessionFactory sessionFactory;

    public UserDetailDaoTest(){
        context = new ClassPathXmlApplicationContext("spring_config_dao_test.xml");
        baseDao = context.getBean("baseDao", BaseDao.class);
        userDetailsDao = context.getBean("userDetailsDao", UserDetailsDao.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @Test
    public void testUserDetailDao(){
        Assert.assertNotNull("Check dao: ", userDetailsDao);
    }



}
