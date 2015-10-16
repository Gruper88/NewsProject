package by.degtev.news.daoTest;


import by.degtev.news.pojos.Category;
import by.degtev.news.pojos.News;
import by.degtev.news.pojos.User;
import by.degtev.news.pojos.UserDetails;
import org.apache.log4j.Logger;

import java.sql.Date;

public abstract class DaoTest {
    private static Logger logger = Logger.getLogger(DaoTest.class);

    protected static User user1;
    protected static User user2;

    protected static UserDetails u1detail;
    protected static UserDetails u2detail;

    protected static News news1;
    protected static News news2;

    protected static Category category1;
    protected static Category category2;


    public UserDetails createObject(UserDetails userDetails){
        userDetails = new UserDetails();
        userDetails.setName("test");
        userDetails.setSurname("test");
        return userDetails;
    }

    public User createObject(User user){
        user = new User();
        user.setEmail("test");
        user.setPassword("test");
        return user;
    }

    public Category createObject(Category category){
        category = new Category();
        category.setCategory("test");
        return category;
    }

    public News createObject(News news){
        news = new News();
        Date date = null;
        date = Date.valueOf("2014-12-31");

        news.setDate(date);
        news.setTitle("test");
        news.setDescription("test");
        news.setNewstext("text");
        return news;
    }


}


