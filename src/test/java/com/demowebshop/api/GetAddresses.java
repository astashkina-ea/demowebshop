package com.demowebshop.api;

import io.qameta.allure.Step;

import java.util.Map;

import static com.demowebshop.api.LoginApi.authCookieKey;
import static com.demowebshop.specs.Specs.*;
import static io.restassured.RestAssured.given;

public class GetAddresses {

    @Step("Make request to get addresses")
    public static String getAddresses(Map<String, String> data, String authCookieValue) {
        return given(requestSpec)
                .cookie(authCookieKey, authCookieValue)
                .when()
                .post("/customer/addresses")
                .then()
                .spec(response200Spec)
                .extract()
                .asString();
    }
}
