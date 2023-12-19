package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.model.base.BasePage;

public class FormPage extends BasePage {

    @FindBy(id = "dataEmail")
    private WebElement emailInputField;

    @FindBy(id = "dataName")
    private WebElement userNameInputField;

    @FindBy(id = "dataGender")
    private WebElement genderDropDownList;

    @FindBy(id = "dataCheck11")
    private WebElement checkBox11;

    @FindBy(id = "dataCheck12")
    private WebElement checkBox12;

    @FindBy(id = "dataSelect21")
    private WebElement checkBox21;

    @FindBy(id = "dataSelect22")
    private WebElement checkBox22;

    @FindBy(id = "dataSelect23")
    private WebElement checkBox23;

    @FindBy(id = "dataSend")
    private WebElement submitButton;

    @FindBy(id = "dataTable")
    private WebElement userTable;

    @FindBy(xpath = "//*[@id=\"dataTable\"]/tbody/tr[1]/td[1]")
    private WebElement userNameFromTable;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage inputEMail(String email) {
        emailInputField.sendKeys(email);

        return new FormPage(getDriver());
    }

    public FormPage inputUserName(String userName) {
        userNameInputField.sendKeys(userName);

        return new FormPage(getDriver());
    }

    public FormPage selectMale() {
        new Select(genderDropDownList).selectByIndex(0);

        return new FormPage(getDriver());
    }

    public FormPage selectFemale() {
        new Select(genderDropDownList).selectByValue("Женский");

        return new FormPage(getDriver());
    }

    public FormPage tickCheckbox11() {
        checkBox11.click();

        return new FormPage(getDriver());
    }

    public FormPage tickCheckbox21() {
        checkBox21.click();

        return new FormPage(getDriver());
    }

    public ConfirmationPage clickSubmit() {
        submitButton.click();

        return new ConfirmationPage(getDriver());
    }

//    public List<String> getUserData() {
//
//        return new ConfirmationPage();
//    }

    public String getUserNameFromTable() {
        return userNameFromTable.getText();
    }
}
