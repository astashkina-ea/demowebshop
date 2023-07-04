package com.demowebshop.api;

import com.demowebshop.models.AddProductToCartResponse;
import io.qameta.allure.Step;

import static com.demowebshop.api.LoginApi.authCookieKey;
import static com.demowebshop.specs.Specs.requestSpec;
import static com.demowebshop.specs.Specs.response200Spec;
import static io.restassured.RestAssured.given;

public class AddProductToCartApi {

    @Step("Make request to add product to cart by auth user")
    public static AddProductToCartResponse addProductToCartWithAuth(String data, String authCookieValue) {
        return given(requestSpec)
                .cookie(authCookieKey, authCookieValue)
                .body(data)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .spec(response200Spec)
                .extract()
                .as(AddProductToCartResponse.class);
    }

    @Step("Make request to add product to cart by unauth user")
    public static AddProductToCartResponse addProductToCartWithUnAuth(String data) {
        return given(requestSpec)
                .body(data)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .spec(response200Spec)
                .extract()
                .as(AddProductToCartResponse.class);
    }
}