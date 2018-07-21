package com.alekseysamoylov.productservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "product")
data class Product(@Id var id: String = "0", var name: String = "Default Product")
