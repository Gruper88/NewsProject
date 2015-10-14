package by.news.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminPage {
    @FindBy(id = "logOutButton")
    private WebElement logOutButton;
    @FindBy(id = "addNews")
    private WebElement addNewsButton;

    public AdminPage logOut() {
        WebDriverManager.getDriverInstance().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //Press button if logOutButton exist on page
        if (!WebDriverManager.getDriverInstance().findElements(By.className("logout")).isEmpty()) {
            logOutButton.click();
            return this;
        } else return this;
    }

    public AdminPage addNews() {
        addNewsButton.click();
        return this;
    }

    public List <WebElement> getPaginationButtonList(){
        return  WebDriverManager.getDriverInstance().findElements(By.xpath("//a[contains(@href,'news_count=5')]"));
    }

    public AdminPage deleteNews(String newsTitle) {
        getDeleteNewsButton(getNewsLink(newsTitle)).click();
        return this;
    }

    public WebElement getNewsLink(String newsTitle) {
        return WebDriverManager.getDriverInstance().findElement(By.linkText(newsTitle));
    }

    public WebElement getDeleteNewsButton(WebElement newsLink) {
        String href = newsLink.getAttribute("href");
        String newsId = StringUtils.substringAfter(href, "?news_id=");
        return WebDriverManager.getDriverInstance().findElement(By.xpath("//input[@value='" + newsId + "']/following::button"));
    }

    public boolean findNewsTitle(String newsTitle) {
        if(WebDriverManager.getDriverInstance().findElements(By.linkText(newsTitle)).isEmpty()){
            return false;
        }
        else return true;
    }
}