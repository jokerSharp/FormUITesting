package ui.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectUtils {

    private static final String EMAIL = "test@protei.ru";
    private static final String PASSWORD = "test";
    public static void login(WebDriver driver) {
        driver.findElement(By.id("loginEmail")).sendKeys(ProjectUtils.EMAIL);
        driver.findElement(By.id("loginPassword")).sendKeys(ProjectUtils.PASSWORD);
        driver.findElement(By.id("authButton")).click();
    }
}
