package com.demowebshop.specs;

import com.demowebshop.helpers.CustomAllureListener;
import com.demowebshop.config.ApiConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {

    private static ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

    public static RequestSpecification requestSpec = with()
            .log().uri()
            .log().body()
            .log().params()
            .filter(CustomAllureListener.withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=utf-8")
            .baseUri(config.getBaseApiUrl());

    public static ResponseSpecification response200Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response302Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(302)
            .build();
}