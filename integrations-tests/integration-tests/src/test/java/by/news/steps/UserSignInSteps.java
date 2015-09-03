package by.news.steps;

import by.news.pages.LoginPage;
import by.news.pages.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class UserSignInSteps {
    WebDriver driver = WebDriverManager.getDriverInstance();
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    /**
     * @param username
     * @param password
     */
    public void userSignInSteps(String username, String password) {
        loginPage.inputUserName(username).inputPassword(password).signIn();
    }

    /**
     * @param driver
     * @return
     */
    public boolean checkErrorMassage(WebDriver driver, String errorMassage) {
        return driver.findElement(By.xpath("//html")).getText().contains(errorMassage);
    }
}
