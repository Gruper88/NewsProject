package by.news.steps;

import by.news.pages.AddNewsPage;
import by.news.pages.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AddNewsPageSteps {

    WebDriver driver = WebDriverManager.getDriverInstance();
    AddNewsPage addNewsPage = PageFactory.initElements(driver, AddNewsPage.class);

    /**
     * @param date
     * @param title
     * @param annotation
     * @param category
     * @param text
     */
    public void addNewsInSteps(String date, String title, String annotation, String category, String text) {
        addNewsPage.inputDate(date)
                .inputTitle(title)
                .inputAnnotation(annotation)
                .inputCategory(category)
                .inputText(text)
                .saveNews();
    }

}
