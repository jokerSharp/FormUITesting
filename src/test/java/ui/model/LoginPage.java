package ui.model;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class LoginPage extends BasePage<LoginPage> {
    private static final String EMAIL = "test@protei.ru";
    private static final String PASSWORD = "test";
    @FindBy(id = "loginEmail")
    private WebElement emailInputField;

    @FindBy(id = "loginPassword")
    private WebElement passwordInputField;

    @FindBy(id = "authButton")
    private WebElement submitButton;

    @FindBy(id = "invalidEmailPassword")
    private WebElement emailPasswordWarningMessage;

    public FormPage login() {
        emailInputField.sendKeys(EMAIL);
        passwordInputField.sendKeys(PASSWORD);
        submitButton.click();

        return new FormPage(getDriver());
    }

    public LoginPage inputEmail(String email) {
        emailInputField.sendKeys(email);

        return new LoginPage(getDriver());
    }

    public LoginPage inputPassword(String password) {
        passwordInputField.sendKeys(password);

        return new LoginPage(getDriver());
    }

    public FormPage clickSubmit() {
        submitButton.click();

        return new FormPage(getDriver());
    }

    public LoginPage clickSubmitWithError() {
        submitButton.click();

        return new LoginPage(getDriver());
    }

    public boolean isPasswordWarningMessageDisplayed() {
        boolean isDisplayed = false;
        try {
            isDisplayed = emailPasswordWarningMessage.isDisplayed();
        } catch (NoSuchElementException ignore) {}

        return isDisplayed;
    }

    public String getPasswordWarningText() {
        return emailPasswordWarningMessage.getText();
    }

    @Override
    protected LoginPage createPage() {
        return new LoginPage(getDriver());
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
