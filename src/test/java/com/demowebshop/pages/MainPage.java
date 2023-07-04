package com.demowebshop.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    @Step("Open main page")
    public MainPage openPage() {
        open("");
        return this;
    }
}