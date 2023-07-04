package com.demowebshop.api;

import com.demowebshop.specs.Specs;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class LoginApi {

    public static String authCookieKey = "NOPCOMMERCE.AUTH";

    @Step("Get authorization cookie")
    public static String getAuthCookie(String login, String password) {
        return given(Specs.requestSpec)
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .spec(Specs.response302Spec)
                .extract()
                .cookie(authCookieKey);
    }
}
