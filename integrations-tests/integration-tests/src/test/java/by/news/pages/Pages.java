package by.news.pages;

import org.openqa.selenium.support.PageFactory;

public class Pages {

    private Pages(){}

    public static <T> T at(Class<T> pageClass){
        return PageFactory.initElements(WebDriverManager.getDriverInstance(), pageClass);
    }
}
