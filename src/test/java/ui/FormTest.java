package ui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.model.FormPage;
import ui.model.FormPage.Gender;
import ui.model.LoginPage;
import ui.runner.BaseTest;

import java.util.List;

public class FormTest extends BaseTest {
    private final static String EMAIL = "test@test.com";
    private final static String USER_NAME = "User Name";

    @DataProvider
    public Object[][] providePositiveData() {
        return new Object[][] {
                {"test@test.test", "Ivan Ivanov", "Мужской"},
                {"test@test.test", "Мария Петрова", "Женский"}
        };
    }

    @Test(dataProvider = "providePositiveData")
    public void addNewUser(String email, String name, String gender) {
        List<String> actualUserData = new LoginPage(getDriver())
                .login()
                .inputEMail(email)
                .inputUserName(name)
                .selectGender(gender)
                .tickCheckbox11()
                .tickCheckbox21()
                .clickSubmit()
                .clickOk()
                .getUserDataFromTable();

        System.out.println(actualUserData);

        Assert.assertTrue(actualUserData.get(0).equals(email)
                && actualUserData.get(1).equals(name)
                && actualUserData.get(2).equals(gender));
    }
}
