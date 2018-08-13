package com.alekseysamoylov.kotlinclient.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

@Component
@RefreshScope
class ClientConfiguration {

    @Value("\${configurable-name}")
    private lateinit var configurableName: String

    fun getConfigurableName(): String {
        return configurableName
    }
}
