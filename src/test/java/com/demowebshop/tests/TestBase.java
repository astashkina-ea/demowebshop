package com.demowebshop.tests;

import com.demowebshop.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestBase {

    public static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
}