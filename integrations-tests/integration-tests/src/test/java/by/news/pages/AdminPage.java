package by.news.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;
import java.util.WeakHashMap;

public class AdminPage {
    private static String URL_MATCH = "adminCommand";
    private WebDriver driver;
    @FindBy(id = "logOutButton")
    private WebElement logOutButton;
    @FindBy(xpath = "/html/body/div/table[4]/tbody/tr/td/form[1]/button")
    private  WebElement addNewsButton;
    @FindBy(xpath = "/html/body/div/table[3]/tbody/tr[6]/td[3]/form/button")
    private  WebElement deleteNewsButton;

    public AdminPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("" +
                    "This is not page you are expected!"
            );
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

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

    public boolean checkLogOutButton(WebDriver driver,String userName){
        return driver.findElement(By.xpath("//html")).getText().contains(userName);
    }
    public boolean findNewsByTitle(WebDriver driver,String newsTitle){
        return driver.findElement(By.xpath("//html")).getText().contains(newsTitle);
    }
}
