package com.demowebshop.models;

import lombok.Data;

@Data
public class AddProductToCartResponse {

    private Boolean success;
    private String message;
    private String updatetopcartsectionhtml;
    private String updateflyoutcartsectionhtml;
}