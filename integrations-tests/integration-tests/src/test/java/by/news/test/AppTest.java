package by.news.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;




import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;

    @BeforeTest
        @Parameters("browser")
    public void setup(String browser) throws Exception{
        if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else {
            throw new Exception("Browser is not correct");

        }
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin(){
        driver.get("http://localhost:8081/admin/adminCommand.html");
        //fill username
        WebElement element = driver.findElement(By.xpath("//*[@id='username_or_email']"));
        element.sendKeys("yura.gruper@gmail.com");
        //fill password
        element = driver.findElement(By.xpath("//*[@id='password']"));
        element.sendKeys("12345");
        // press Submit button
        new Actions(driver)

                .click(driver.findElement(By.xpath("//input[@value='Send']")))
                .perform();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(scrFile, new File("SuccessLogin.jpg"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(driver.findElement(By.xpath("//html")).getText().contains("Access denied!!!"));
    }

}
