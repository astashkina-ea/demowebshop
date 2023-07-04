package com.demowebshop.api;

import io.qameta.allure.Step;

import static com.demowebshop.api.LoginApi.authCookieKey;
import static com.demowebshop.specs.Specs.requestSpec;
import static com.demowebshop.specs.Specs.response200Spec;
import static io.restassured.RestAssured.given;

public class GetCartApi {

    @Step("Make request to get cart")
    public static String getCart(String authCookieValue) {
        return given(requestSpec)
                .cookie(authCookieKey, authCookieValue)
                .when()
                .get()
                .then()
                .spec(response200Spec)
                .extract()
                .asString();
    }
}
