package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.model.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

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
    private WebElement radioButton21;

    @FindBy(id = "dataSelect22")
    private WebElement radioButton22;

    @FindBy(id = "dataSelect23")
    private WebElement radioButton23;

    @FindBy(id = "dataSend")
    private WebElement submitButton;

    @FindBy(id = "dataTable")
    private WebElement userTable;

    @FindBy(xpath = "//table/tbody/tr[1]/td")
    private List<WebElement> firstRowOfUserTable;

    @FindBy(id = "emailFormatError")
    private WebElement wrongEmailFormatMessage;

    @FindBy(id = "blankNameError")
    private WebElement blankNameMessage;

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

    public FormPage selectGender(String gender) {
        if (gender.equals("Мужской")) {
            new Select(genderDropDownList).selectByIndex(0);
        } else {
            new Select(genderDropDownList).selectByIndex(1);
        }

        return new FormPage(getDriver());
    }

    public FormPage tickCheckbox11() {
        checkBox11.click();

        return new FormPage(getDriver());
    }

    public FormPage tickCheckbox21() {
        radioButton21.click();

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

    public List<String> getUserDataFromTable() {

        return firstRowOfUserTable.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public enum Gender {
        Male,
        Female
    }
}
