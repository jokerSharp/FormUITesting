package ui.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class LoginPage extends BasePage {
    private static final String USER_NAME = "test@protei.ru";
    private static final String PASSWORD = "test";
    @FindBy(id = "loginEmail")
    private WebElement emailInputField;

    @FindBy(id = "loginPassword")
    private WebElement loginPassword;

    @FindBy(id = "authButton")
    private WebElement submitButton;

    public FormPage login() {
        emailInputField.sendKeys(USER_NAME);
        loginPassword.sendKeys(PASSWORD);
        submitButton.click();

        return new FormPage(getDriver());
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
