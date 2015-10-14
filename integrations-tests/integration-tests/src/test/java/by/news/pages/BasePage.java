package by.news.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class BasePage {
    @FindBy(xpath = "//h3")
    private WebElement errorMassageElement;

    public boolean checkErrorMassage(String errorMassage){
        if (!WebDriverManager.getDriverInstance().findElements(By.xpath("//h3")).isEmpty()){
            return errorMassageElement.getText().contains(errorMassage);
        }
        else return false;
    }
}
