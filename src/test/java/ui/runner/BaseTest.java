package ui.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

import static ui.runner.ProjectUtils.login;

public abstract class BaseTest {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        URL htmlFile = BaseTest.class.getClassLoader().getResource("qa-test.html");
        driver.get(htmlFile.toString());
    }

    @AfterMethod
    public static void teatDown() {
        driver.quit();
    }
}
