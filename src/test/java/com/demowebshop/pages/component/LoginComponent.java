package com.demowebshop.pages.component;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginComponent {

    private SelenideElement emailField = $("#Email"),
            passwordField = $("#Email");

    @Step("Open login page")
    public LoginComponent openPage() {
        open("/login");
        return this;
    }

    @Step("Fill login form")
    public LoginComponent fillLoginForm(String login, String password) {
        emailField.setValue(login);
        passwordField.setValue(password).pressEnter();
        return this;
    }
}