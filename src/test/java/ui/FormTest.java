package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.FormPage;
import ui.model.LoginPage;
import ui.runner.BaseTest;

public class FormTest extends BaseTest {
    private final static String EMAIL = "test@test.com";
    private final static String USER_NAME = "User Name";

    @Test
    public void addNewUser() {
        String actualUserName = new LoginPage(getDriver())
                .login()
                .inputEMail(EMAIL)
                .inputUserName(USER_NAME)
                .selectMale()
                .tickCheckbox11()
                .tickCheckbox21()
                .clickSubmit()
                .clickOk()
                .getUserNameFromTable();

        Assert.assertEquals(actualUserName, EMAIL);
    }
}
