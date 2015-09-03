package by.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage {
    private static String URL_MATCH = "adminCommand";
    private WebDriver driver;
    @FindBy(id = "logOutButton")
    private WebElement logOutButton;
    @FindBy(xpath = "/html/body/div/table[4]/tbody/tr/td/form[1]/button")
    private  WebElement addNewsButton;
    @FindBy(xpath = "/html/body/div/table[3]/tbody/tr[6]/td[3]/form/button")
    private  WebElement deleteNewsButton;

    public AdminPage logOut(){
        logOutButton.click();
        return this;
    }
    public AdminPage addNews(){
        addNewsButton.click();
        return this;
    }
    public AdminPage deleteNews(){
        deleteNewsButton.click();
        return this;
    }

}
