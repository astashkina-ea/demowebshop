package com.demowebshop.tests.uiTests;

import com.codeborne.selenide.Condition;
import com.demowebshop.api.LoginApi;
import com.demowebshop.config.CredentialsConfig;
import com.demowebshop.pages.MainPage;
import com.demowebshop.pages.component.HeaderComponent;
import com.demowebshop.pages.component.LoginComponent;
import com.demowebshop.tests.UiTestBase;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demowebshop.api.LoginApi.authCookieKey;

public class LoginTests extends UiTestBase {

    private static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    LoginComponent loginComponent = new LoginComponent();
    HeaderComponent headerComponent = new HeaderComponent();
    MainPage mainPage = new MainPage();

    String emailValue = config.getLoginAccount();
    String passwordValue = config.getPasswordAccount();

    @Test
    void loginTest() {
        loginComponent.openPage()
                .fillLoginForm(emailValue, passwordValue);
        headerComponent.checkSuccessfulAuth(emailValue);
    }

    @Test
    void loginWithApiTest() {
        String authCookieValue = LoginApi.getAuthCookie(emailValue, passwordValue);

        open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
        Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
        getWebDriver().manage().addCookie(authCookie);

        mainPage.openPage();
        headerComponent.checkSuccessfulAuth(emailValue);
    }
}