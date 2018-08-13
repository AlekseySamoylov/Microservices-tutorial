package com.alekseysamoylov.oauth.domain

import java.io.Serializable
import java.security.Principal


class User constructor(private var name: String = "") : Principal, Serializable {
    lateinit var roles: List<Role>
    lateinit var email: String
    var password: String = "secret"
    override fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}
