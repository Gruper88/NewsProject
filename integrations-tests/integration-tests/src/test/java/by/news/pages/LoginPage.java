package by.news.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static String URL_MATCH = "login";
    private static String errorMassage = "Access denied!!!";
    private WebDriver driver;

    @FindBy(id = "username_or_email")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(name = "commit")
    private WebElement signinButton;

    public LoginPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("" +
                    "This is not page you are expected!"
            );
        }
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }


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

    public void userSignInSteps(String username,String password){
        this.inputUserName(username).inputPassword(password).signIn();
    }

    public boolean checkErrorMassage(WebDriver driver){
        return driver.findElement(By.xpath("//html")).getText().contains(errorMassage);
    }


}
