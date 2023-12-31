package com.demowebshop.tests.uiTests;

import com.demowebshop.api.LoginApi;
import com.demowebshop.pages.MainPage;
import com.demowebshop.pages.component.HeaderComponent;
import com.demowebshop.pages.component.LoginComponent;
import com.demowebshop.tests.UiTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demowebshop.api.LoginApi.authCookieKey;

@Tag("ui")
public class LoginTests extends UiTestBase {

    LoginComponent loginComponent = new LoginComponent();
    HeaderComponent headerComponent = new HeaderComponent();
    MainPage mainPage = new MainPage();

    String emailValue = credentialsConfig.getLoginAccount();
    String passwordValue = credentialsConfig.getPasswordAccount();

    @Test
    @DisplayName("Login from web")
    void loginTest() {
        loginComponent.openPage()
                .fillLoginForm(emailValue, passwordValue);
        headerComponent.checkSuccessFulAuth(emailValue);
    }

    @Test
    @DisplayName("Login from api")
    void loginWithApiTest() {
        String authCookieValue = LoginApi.getAuthCookie(emailValue, passwordValue);

        open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
        Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
        getWebDriver().manage().addCookie(authCookie);

        mainPage.openPage();
        headerComponent.checkSuccessFulAuth(emailValue);
    }
}