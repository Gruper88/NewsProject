package by.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;

public class WebDriverManager {
    private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();

    public static WebDriver getDriverInstance() {
        WebDriver d = map.get(Thread.currentThread().getId());
        return d;
    }

    public static WebDriver startDriver(String type, String chromeURL) {
        WebDriver driver;

        if (type.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeURL);
            driver = new ChromeDriver();
            map.put(Thread.currentThread().getId(), driver);
        } else if (type.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            map.put(Thread.currentThread().getId(), driver);
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + type);
        }
        return driver;
    }

    public static void stopDriver() {
        WebDriver d = map.get(Thread.currentThread().getId());
        d.close();
    }
}

