package by.degtev.news;


import by.degtev.news.services.BaseService;
import by.degtev.news.services.CategoryService;
import by.degtev.news.services.NewsService;
import by.degtev.news.services.UserService;
import org.springframework.context.ApplicationContext;

public abstract class ServiceTest {

    protected static ApplicationContext context;
    protected static BaseService baseService;
    protected static UserService userService;
    protected static CategoryService categoryService;
    protected static NewsService newsService;


    /**
     * preparation of a database.
     */
    //@Before
    public void BeforeDao() {
    }

    /**
     * Cleaning database
     */
    //@After
    public void AfterDao() {

    }

}
