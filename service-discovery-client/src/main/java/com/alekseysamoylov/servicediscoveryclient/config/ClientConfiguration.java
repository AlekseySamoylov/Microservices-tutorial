package com.alekseysamoylov.servicediscoveryclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ClientConfiguration {

    @Value("${configurable-name}")
    private String configurableName;

    public String getConfigurableName() {
        return configurableName;
    }
}
