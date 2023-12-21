package ui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.model.FormPage;
import ui.model.LoginPage;
import ui.runner.BaseTest;

import java.util.List;

public class FormTest extends BaseTest {
    private final static String EMAIL = "test@test.com";
    private final static String USER_NAME = "User Name";
    private final static String XSS_SCRIPT = "<script>alert(‘XSS’)</script>";
    private final static String EMAIL_NEGATIVE = "test";

    @DataProvider
    public Object[][] providePositiveData() {
        return new Object[][] {
                {"test@test.test", "Ivan Ivanov", "Мужской"},
                {"test@test.test", "Мария Петрова", "Женский"}
        };
    }

    @DataProvider
    public Object[][] provideNegativeEmailData() {
        return new Object[][] {
                {""},
                {" "},
                {"test@"},
                {"test@ "},
                {"test@test"}
        };
    }

    @DataProvider
    public Object[][] provideNegativeNameData() {
        return new Object[][] {
                {""},
                {" "}
        };
    }

    @Test(dataProvider = "providePositiveData")
    public void testAddNewUser(String email, String name, String gender) {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(email)
                .inputUserName(name)
                .selectGender(gender)
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.get(0).equals(email)
                && actualUserData.get(1).equals(name)
                && actualUserData.get(2).equals(gender));
    }

    @Test
    public void testTickCheckBox11() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .tickCheckbox11()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("1.1"));
    }

    @Test
    public void testTickCheckBox12() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .tickCheckbox12()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("1.2"));
    }

    @Test
    public void testTickAllCheckBoxes() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .tickCheckbox11()
                .tickCheckbox12()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("1.1, 1.2"));
    }

    @Test
    public void testUntickAllCheckBoxes() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .tickCheckbox11()
                .tickCheckbox11()
                .tickCheckbox12()
                .tickCheckbox12()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("Нет"));
    }

    @Test
    public void testSelectRadiobutton21() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .selectRadiobutton21()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("2.1"));
    }

    @Test
    public void testSelectRadiobutton22() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .selectRadiobutton22()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("2.2"));
    }

    @Test
    public void testSelectRadiobutton23() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .selectRadiobutton23()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("2.3"));
    }

    @Test
    public void testReselectRadiobutton() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .selectRadiobutton21()
                .selectRadiobutton23()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains("2.3"));
    }

    @Test(dataProvider = "provideNegativeEmailData")
    public void testShowEmailErrorMessage(String email) {
        boolean emailWarningMessageStatus = new LoginPage(getDriver())
                .login()
                .inputEMail(email)
                .clickSubmitWithError()
                .isEmailWarningDisplayed();

        Assert.assertTrue(emailWarningMessageStatus);
    }

    @Test(dataProvider = "provideNegativeNameData")
    public void testShowNameErrorMessage(String name) {
        boolean nameWarningMessageStatus = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(name)
                .clickSubmitWithError()
                .isNameWarningDisplayed();

        Assert.assertTrue(nameWarningMessageStatus);
    }

    @Test
    public void testXSSScripts() {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(XSS_SCRIPT + "@" + XSS_SCRIPT)
                .inputUserName(XSS_SCRIPT)
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        Assert.assertTrue(actualUserData.contains(XSS_SCRIPT));
    }

    @Test
    public void testWarningMessageText() {
        String actualMessage = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL_NEGATIVE)
                .clickSubmitWithError()
                .getEmailWarningText();

        Assert.assertEquals(actualMessage, "Неверный формат E-Mail");
    }

    @Test
    public void testCloseWarningMessage() {
        boolean isDisplayed = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL_NEGATIVE)
                .clickSubmitWithError()
                .closeAlert(new FormPage(getDriver()))
                .isEmailWarningDisplayed();

        Assert.assertFalse(isDisplayed);
    }
}
