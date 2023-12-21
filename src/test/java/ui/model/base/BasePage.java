package ui.model.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    private final WebDriver driver;
    @FindBy(xpath = "//div[@id='emailFormatError']")
    private WebElement wrongEmailFormatMessage;

    @FindBy(className = "uk-alert-close")
    private WebElement closeAlertButton;

    public String getEmailWarningText() {

        return wrongEmailFormatMessage.getText();
    }

    public boolean isEmailWarningDisplayed() {
        boolean isDisplayed = false;
        try {
            isDisplayed = wrongEmailFormatMessage.isDisplayed();
        } catch (NoSuchElementException ignore) {}

        return isDisplayed;
    }

    public <T extends BasePage> T  closeAlert(T page) {
        new Actions(getDriver())
                .click(closeAlertButton)
                .pause(300)
                .perform();

        return page;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
