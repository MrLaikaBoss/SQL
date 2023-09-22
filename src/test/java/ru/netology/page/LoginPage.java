package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public void verifyErrorNotification(String expectedText) {
        $("[data-test-id=error-notification]").shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {

        $("[data-test-id=login] .input__control").setValue(info.getLogin());
        $("[data-test-id=password] .input__control").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
    public void invalidLogin(DataHelper.AuthInfo info) {

        $("[data-test-id=login] .input__control").setValue(info.getLogin());
        $("[data-test-id=password] .input__control").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();

    }

}
