package by.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private static String URL_MATCH = "login";
    private WebDriver driver;

    @FindBy(id = "username_or_email")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(name = "commit")
    private WebElement signinButton;


    public LoginPage inputUserName(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String pasword) {
        passwordInput.sendKeys(pasword);
        return this;
    }

    public LoginPage signIn() {
        signinButton.click();
        return this;
    }

}
