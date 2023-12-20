package ui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.model.LoginPage;
import ui.runner.BaseTest;

public class LoginTest extends BaseTest {
    private static final String EMAIL = "test@protei.ru";
    private static final String PASSWORD = "test";

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
                .isWarningMessageDisplayed();

        Assert.assertTrue(isWarningDisplayed);
    }
}
