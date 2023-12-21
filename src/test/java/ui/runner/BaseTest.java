package ui.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

public abstract class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        URL htmlFile = getClass().getClassLoader().getResource("qa-test.html");
        driver.get(htmlFile.toString());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
