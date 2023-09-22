package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyBottom = $("[data-test-id=action-verify]");


    public void verificationPageVisible() {
        codeField.shouldBe(visible);
    }

    public void verifyErrorNotification(String expectedText) {
        $("[data-test-id=error-notification] .notification__content").shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void verifyBlockedNotification(String expectedText) {
        $("[data-test-id=error-notification] .notification__content").shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void verifyEmptyNotification(String expectedText) {
        $("[data-test-id=code] .input__sub").shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }

    public void invalidVerify() {
        verifyBottom.click();
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyBottom.click();
    }
}
