package com.alekseysamoylov.productservice.repository

import com.alekseysamoylov.productservice.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String>
