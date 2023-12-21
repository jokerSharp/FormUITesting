package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class ConfirmationPage extends BasePage<ConfirmationPage> {

    @FindBy(xpath = "//button[text()='Ok']")
    private WebElement okButton;

    public FormPage clickOk() {
        okButton.click();

        return new FormPage(getDriver());
    }

    @Override
    protected ConfirmationPage createPage() {
        return new ConfirmationPage(getDriver());
    }

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }
}
