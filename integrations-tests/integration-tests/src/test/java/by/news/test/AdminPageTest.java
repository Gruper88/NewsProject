package by.news.test;

import by.news.pages.WebDriverManager;
import by.news.dataprovider.TestParameterDataProvider;
import java.util.Map;

import by.news.model.News;
import by.news.steps.AdminPageSteps;
import by.news.steps.UserSignInSteps;
import org.testng.Assert;
import org.testng.annotations.*;

public class AdminPageTest {

    @BeforeTest
    @Parameters({"browser","browserDriverURL"})
    public void setup(String browser,String browserDriverURL) throws Exception {
        WebDriverManager.startDriver(browser, browserDriverURL);
    }

    @Test(dataProvider = "newsData", dataProviderClass =TestParameterDataProvider.class)
    public void testLogin(News news, Map<String, String> testData) {

        WebDriverManager.getDriverInstance().get(testData.get("loginPageURL"));
        UserSignInSteps userSteps = new UserSignInSteps();
        userSteps.userSignInSteps(testData.get("username"), testData.get("password"));
        Assert.assertFalse(userSteps.checkErrorMassage(WebDriverManager.getDriverInstance(), testData.get("errorMassage")));
        AdminPageSteps adminPageSteps = new AdminPageSteps();
        adminPageSteps.logOut();
    }

    /*
        @Test
        public void testLogOut(){
            driver.get(loginPageURL);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.userSignInSteps(username, password);
            AdminPage adminPage= new AdminPage(driver);
            adminPage.logOut();
            Assert.assertFalse(adminPage.checkLogOutButton(driver,username));
        }

        @Test
        public void testAddNews(){
            driver.get(loginPageURL);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.userSignInSteps(username, password);
            AdminPage adminPage= new AdminPage(driver);
            adminPage.addNews();
            AddNewsPage addNewsPage = new AddNewsPage(driver);
            addNewsPage.addNewsInSteps(newsDate,newsTitle,newsAnnotation,newsCategory,newsText); // Add news
            //boolean newsOnPage = adminPage.findNewsByTitle(driver,newsTitle);   //Check added news
            adminPage.deleteNews(); //Delete added news
            adminPage.logOut();
            //Assert.assertTrue(newsOnPage,"Added news not found!");

        }
    */
    @AfterTest
    public void endTests() {
        WebDriverManager.stopDriver();
    }
}
