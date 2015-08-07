package by.news.test;

import by.news.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class LoginPageTest {
    WebDriver driver;
    private static String loginPageURL = "http://localhost:8081/admin/adminCommand.html";
    String username = "yura.gruper@gmail.com";
    String password = "12345";

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver.exe");
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
        loginPage.userSignInSteps(username,password);
        Assert.assertFalse(loginPage.checkErrorMassage(driver));
    }

    @AfterTest
    public void endTests(){
        driver.quit();
    }

}
