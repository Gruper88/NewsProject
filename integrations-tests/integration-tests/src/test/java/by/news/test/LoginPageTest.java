package by.news.test;

import by.news.config.Path;
import by.news.pages.AdminPage;
import by.news.pages.LoginPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class LoginPageTest {
    WebDriver driver;
    private String username;
    private String password;
    private String chromeURL;
    private String loginPageURL;
    private static String TEST_PROPERTIES_FILE = Path.TEST_PROPERTIES_FILE;



    @BeforeTest
    public void initData(){
        Properties testProperties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TEST_PROPERTIES_FILE);
        try {
            testProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chromeURL = testProperties.getProperty("chromeDriverProperties");
        loginPageURL = testProperties.getProperty("loginPage");
        username = testProperties.getProperty("username");
        password = testProperties.getProperty("password");
    }

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeURL);
            driver = new ChromeDriver();
        } else {
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() {
        driver.get(loginPageURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userSignInSteps(username, password);
        Assert.assertFalse(loginPage.checkErrorMassage(driver));
    }

    @Test
    public void testLogOut(){
        driver.get(loginPageURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userSignInSteps(username, password);
        AdminPage adminPage= new AdminPage(driver);
        adminPage.logOut();
        Assert.assertFalse(adminPage.checkLogOutButton(driver,username));
    }

    @AfterTest
    public void endTests() {
        driver.close();
    }


}
