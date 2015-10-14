package by.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> WD_INSTANCE = new ThreadLocal<WebDriver>();

    public static WebDriver getDriverInstance() {
        WebDriver driver = WD_INSTANCE.get();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver startDriver(String type, String chromeURL) {
        WebDriver driver;

        if (type.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeURL);
            driver = new ChromeDriver();
        } else if (type.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + type);
        }
        WD_INSTANCE.set(driver);
        return WD_INSTANCE.get();
    }

    public static void stopDriver() {
       WD_INSTANCE.get().quit();
       WD_INSTANCE.set(null);
    }
}

