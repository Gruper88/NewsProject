package by.news.steps;

import by.news.pages.AdminPage;
import by.news.pages.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AdminPageSteps {
    WebDriver driver = WebDriverManager.getDriverInstance();
    AdminPage adminPage = PageFactory.initElements(driver, AdminPage.class);

    /**
     * @param driver
     * @param userName
     * @return
     */
    public boolean checkLogOutButton(WebDriver driver, String userName) {
        return driver.findElement(By.xpath("//html")).getText().contains(userName);
    }

    /**
     * @param driver
     * @param newsTitle
     * @return
     */
    public boolean findNewsByTitle(WebDriver driver, String newsTitle) {
        return driver.findElement(By.xpath("//html")).getText().contains(newsTitle);
    }

    public void logOut() {
        adminPage.logOut();
    }
}
