package by.news.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewsPage {
    @FindBy(id = "datepicker")
    private WebElement dateInput;
    @FindBy(id = "title")
    private WebElement titleInput;
    @FindBy(id = "description")
    private WebElement annotationInput;
    @FindBy(name = "category_1")
    private WebElement categoryInput;
    @FindBy(id = "newstext")
    private WebElement textInput;
    @FindBy(id = "newsEditFormButton")
    private WebElement seveNewsButton;

    public AddNewsPage inputDate(String date) {
        dateInput.sendKeys(date);
        return this;
    }

    public AddNewsPage inputTitle(String title) {
        titleInput.sendKeys(title);
        return this;
    }

    public AddNewsPage inputAnnotation(String annotation) {
        annotationInput.sendKeys(annotation);
        return this;
    }

    public AddNewsPage inputCategory(String category) {
        categoryInput.sendKeys(category);
        return this;
    }

    public AddNewsPage inputText(String text) {
        textInput.sendKeys(text);
        return this;
    }
    public AddNewsPage saveNews() {
        seveNewsButton.click();
        return this;
    }

}
