package com.alekseysamoylov.orderservice.model

import java.io.Serializable

data class Product(var id: String? = null, var name: String = "") : Serializable
