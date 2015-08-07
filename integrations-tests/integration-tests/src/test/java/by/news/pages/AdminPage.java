package by.news.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.WeakHashMap;

public class AdminPage {
    private static String URL_MATCH = "adminCommand";
    private WebDriver driver;
    @FindBy(id = "logOutButton")
    private WebElement logOutButton;

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
    public boolean checkLogOutButton(WebDriver driver,String userName){
        return driver.findElement(By.xpath("//html")).getText().contains(userName);
    }
}
