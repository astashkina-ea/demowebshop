package com.demowebshop.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static com.demowebshop.api.LoginApi.authCookieKey;
import static com.demowebshop.specs.Specs.*;
import static io.restassured.RestAssured.given;

public class AddressEditApi {

    @Step("Make request to edit address")
    public static ValidatableResponse addressEdit(Map<String, String> data, String authCookieValue, String id) {
        return given(requestSpec)
                .cookie(authCookieKey, authCookieValue)
                .formParams(data)
                .when()
                .post("/customer/addressedit/" + id)
                .then()
                .spec(response302Spec);
    }
}
