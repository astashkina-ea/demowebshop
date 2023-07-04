package com.demowebshop.pages.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    private SelenideElement accountLink = $(".account");

    @Step("Check successful authorization")
    public HeaderComponent checkSuccessfulAuth(String login) {
        accountLink.shouldHave(Condition.text(login));
        return this;
    }
}
