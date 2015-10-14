package by.news.test;

import by.news.model.News;
import by.news.model.NewsFactory;
import by.news.pages.WebDriverManager;
import by.news.steps.AddNewsPageSteps;
import by.news.steps.AdminPageSteps;
import by.news.steps.UserSignInSteps;
import by.news.testConfiguration.TestConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

@ContextConfiguration(locations = {"classpath:spring_config_tests.xml"})
public class AdminPageTest extends AbstractTestNGSpringContextTests {

    private String browser;

    private String browserDriverURL;

    private String errorMassage;

    @Autowired
    private TestConfiguration conf;

    @Autowired
    private AddNewsPageSteps addNewsPageSteps;

    @Autowired
    private UserSignInSteps userSteps;

    @Autowired
    private NewsFactory newsFactory;

    @BeforeTest
    @Parameters({"browser", "browserDriverURL", "errorMassage"})
    public void setup(String browser, String browserDriverURL, String errorMassage) throws Exception {
        this.errorMassage = errorMassage;
        this.browser = browser;
        this.browserDriverURL = browserDriverURL;
    }

    @BeforeMethod
    public void openApp() {
        WebDriverManager.startDriver(browser, browserDriverURL);
        WebDriverManager.getDriverInstance().get(conf.getLoginPage());
    }

    @Test
    public void testLogin() {
        WebDriverManager.getDriverInstance().get(conf.getLoginPage());
        UserSignInSteps userSteps = new UserSignInSteps();
        userSteps.userSignInSteps(conf.getUseremail(), conf.getPassword());
        Assert.assertFalse(userSteps.checkErrorMassage(errorMassage),"Invalid user data!");
    }

    @Test(dependsOnMethods = "testLogin")
    public void testAddNews() {
        News news = newsFactory.getRandomNews();
        userSteps.userSignInSteps(conf.getUseremail(), conf.getPassword());
        AdminPageSteps adminPageSteps = new AdminPageSteps();
        adminPageSteps.addNews();
        addNewsPageSteps.addNewsInSteps(news);
        Assert.assertTrue(adminPageSteps.findNewsByTitle(news.getTitle()), "Added news not found!");
        adminPageSteps.deleteNews(news.getTitle());
    }

    @AfterMethod
    public void endTests() {
        AdminPageSteps adminPageSteps = new AdminPageSteps();
        adminPageSteps.logOut();
        WebDriverManager.stopDriver();
    }
}
