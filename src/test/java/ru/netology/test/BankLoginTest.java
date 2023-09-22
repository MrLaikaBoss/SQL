package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuthCode;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class BankLoginTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        cleanAuthCode();
    }

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @Test
    @DisplayName("test1")
    void test1() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    @DisplayName("test2")
    void test2() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getRandomUser();
        loginPage.invalidLogin(authInfo);
        loginPage.verifyErrorNotification("Ошибка \nОшибка! Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("test3")
    void test3() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }

    @Test
    @DisplayName("test4")
    void test4() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        verificationPage.invalidVerify();
        verificationPage.verifyEmptyNotification("Поле обязательно для заполнения");

    }

    @Test
    @DisplayName("test5")
    void test5() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCodeFirst = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCodeFirst.getCode());
        verificationPage.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
        var verificationCodeSecond = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCodeSecond.getCode());
        verificationPage.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
        var verificationCodeThird = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCodeThird.getCode());
        verificationPage.verifyBlockedNotification("Доступ заблокирован. Обратитесь в Банк.");
    }

}
