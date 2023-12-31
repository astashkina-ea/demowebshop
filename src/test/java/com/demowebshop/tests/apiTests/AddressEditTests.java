package com.demowebshop.tests.apiTests;

import com.demowebshop.api.LoginApi;
import com.github.javafaker.Faker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.demowebshop.api.AddressEditApi.addressEdit;
import static com.demowebshop.api.GetAddresses.getAddresses;
import static com.demowebshop.tests.TestBase.credentialsConfig;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Tag("api")
public class AddressEditTests {

    String emailValue = credentialsConfig.getLoginAccount();
    String passwordValue = credentialsConfig.getPasswordAccount();

    @Test
    @DisplayName("Add address")
    void addAddressTest() {
        String authCookieValue = LoginApi.getAuthCookie(emailValue, passwordValue);

        String id = "3150008";

        Faker faker = new Faker();
        Map<String, String> data = new HashMap<>();
        data.put("Address.Id", id);
        data.put("Address.FirstName", faker.name().firstName());
        data.put("Address.LastName", faker.name().lastName());
        data.put("Address.Email", faker.internet().emailAddress());
        data.put("Address.Company", faker.company().name());
        data.put("Address.CountryId", Integer.toString(faker.random().nextInt(1, 237)));
        data.put("Address.StateProvinceId", "0");
        data.put("Address.City", faker.address().city());
        data.put("Address.Address1", faker.address().countryCode());
        data.put("Address.Address2", faker.address().fullAddress());
        data.put("Address.ZipPostalCode", faker.address().zipCode());
        data.put("Address.PhoneNumber", faker.phoneNumber().cellPhone());
        data.put("Address.FaxNumber", faker.phoneNumber().subscriberNumber());

        addressEdit(data, authCookieValue, id);

        String page = getAddresses(data, authCookieValue);

        Document document = Jsoup.parse(page);

        step("Check response", () -> assertAll(
                () -> assertEquals(document.getElementsByClass("phone").text(),
                        "Phone number: " + data.get("Address.PhoneNumber")),
                () -> assertEquals(document.getElementsByClass("title").text(),
                        "My account " + data.get("Address.FirstName") + " " + data.get("Address.LastName")),
                () -> assertEquals(document.select("li.name").first().text(),
                        data.get("Address.FirstName") + " " + data.get("Address.LastName")),
                () -> assertEquals(document.getElementsByClass("email").text(),
                        "Email: " + data.get("Address.Email")),
                () -> assertEquals(document.getElementsByClass("fax").text(),
                        "Fax number: " + data.get("Address.FaxNumber")),
                () -> assertEquals(document.getElementsByClass("company").text(),
                        data.get("Address.Company")),
                () -> assertEquals(document.getElementsByClass("address1").text(),
                        data.get("Address.Address1")),
                () -> assertEquals(document.getElementsByClass("address2").text(),
                        data.get("Address.Address2"))
        ));
    }
}