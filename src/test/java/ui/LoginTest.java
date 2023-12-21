package ui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.model.LoginPage;
import ui.runner.BaseTest;

public class LoginTest extends BaseTest {
    private static final String EMAIL = "test@protei.ru";
    private static final String EMAIL_NEGATIVE = "test";
    private static final String PASSWORD = "test";
    private static final String EMAIL_VALIDATION_MESSAGE = "Неверный формат E-Mail";
    private static final String CREDENTIALS_VALIDATION_MESSAGE = "Неверный E-Mail или пароль";

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
    public Object[][] provideNegativePasswordData() {
        return new Object[][] {
                {""},
                {" "},
                {"TEST"},
                {" test "}
        };
    }

    @Test
    public void testInputValidCredentials() {
        boolean isLoggedIn = new LoginPage(getDriver())
                .inputEmail(EMAIL)
                .inputPassword(PASSWORD)
                .clickSubmit()
                .isTableDisplayed();

        Assert.assertTrue(isLoggedIn);
    }

    @Test(dataProvider = "provideNegativeEmailData")
    public void testInputInvalidEmail(String email) {
        boolean isWarningDisplayed = new LoginPage(getDriver())
                .inputEmail(email)
                .clickSubmitWithError()
                .isEmailWarningDisplayed();

        Assert.assertTrue(isWarningDisplayed);
    }

    @Test(dataProvider = "provideNegativePasswordData")
    public void testInputInvalidCredentials(String password) {
        boolean isWarningDisplayed = new LoginPage(getDriver())
                .inputEmail(EMAIL)
                .inputPassword(password)
                .clickSubmitWithError()
                .isPasswordWarningMessageDisplayed();

        Assert.assertTrue(isWarningDisplayed);
    }

    @Test
    public void testCloseWarningMessage() {
        boolean isDisplayed = new LoginPage(getDriver())
                .inputEmail(EMAIL_NEGATIVE)
                .clickSubmitWithError()
                .clickCloseAlert()
                .isEmailWarningDisplayed();

        Assert.assertFalse(isDisplayed);
    }

    @Test
    public void testWarningEmailText() {
        String actualMessage = new LoginPage(getDriver())
                .inputEmail(EMAIL_NEGATIVE)
                .clickSubmitWithError()
                .getEmailWarningText();

        Assert.assertEquals(actualMessage, EMAIL_VALIDATION_MESSAGE);
    }

    @Test
    public void testWarningPasswordText() {
        String actualMessage = new LoginPage(getDriver())
                .inputEmail(EMAIL)
                .clickSubmitWithError()
                .getPasswordWarningText();

        Assert.assertEquals(actualMessage, CREDENTIALS_VALIDATION_MESSAGE);
    }
}
