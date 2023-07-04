package com.demowebshop.tests.apiTests;

import com.demowebshop.api.LoginApi;
import com.demowebshop.config.CredentialsConfig;
import com.demowebshop.models.AddProductToCartResponse;

import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.demowebshop.api.AddProductToCartApi.addProductToCartWithAuth;
import static com.demowebshop.api.AddProductToCartApi.addProductToCartWithUnAuth;
import static com.demowebshop.api.GetCartApi.getCart;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;


@Tag("api")
public class AddProductToCartTests {

    private static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    String emailValue = config.getLoginAccount();
    String passwordValue = config.getPasswordAccount();

    int numberOfItems;
    int quantity = 3;
    String data = "product_attribute_72_5_18=52" +
            "&product_attribute_72_6_19=54" +
            "&product_attribute_72_3_20=58" +
            "&addtocart_72.EnteredQuantity=" + quantity;

    @Test
    @DisplayName("Add product to cart as authorized")
    void addProductToCartAsAuthorizedTest() {
        String authCookieValue = LoginApi.getAuthCookie(emailValue, passwordValue);

        String page = getCart(authCookieValue);

        Document document = Jsoup.parse(page);
        numberOfItems = Integer.parseInt(document.select(".cart-qty")
                .text().replaceAll("[()]", ""));

        AddProductToCartResponse addProductToCartResponse = addProductToCartWithAuth(data, authCookieValue);

        step("Check response", () -> assertAll(
                () -> assertTrue(addProductToCartResponse.getSuccess()),
                () -> assertEquals(addProductToCartResponse.getMessage(), "The product has been added to your <a href=\"/cart\">shopping cart</a>"),
                () -> assertEquals(addProductToCartResponse.getUpdatetopcartsectionhtml(), "(" + (numberOfItems + quantity) + ")")
        ));
    }

    @Test
    @DisplayName("Add product to cart as anonym")
    void addProductToCartAsAnonymTest() {
        AddProductToCartResponse addProductToCartResponse = addProductToCartWithUnAuth(data);

        step("Check response", () -> assertAll(
                () -> assertTrue(addProductToCartResponse.getSuccess()),
                () -> assertEquals(addProductToCartResponse.getMessage(), "The product has been added to your <a href=\"/cart\">shopping cart</a>"),
                () -> assertEquals(addProductToCartResponse.getUpdatetopcartsectionhtml(), "(" + (numberOfItems + quantity) + ")")
        ));
    }
}