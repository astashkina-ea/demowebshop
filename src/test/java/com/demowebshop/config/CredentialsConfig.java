package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/credentials.properties"
})

public interface CredentialsConfig extends Config {
    @Key("email")
    String getLoginAccount();

    @Key("password")
    String getPasswordAccount();
}