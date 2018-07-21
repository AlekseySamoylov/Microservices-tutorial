package com.alekseysamoylov.productservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfiguration {

    @Bean
    fun mongoTemplate(mongoDbFactory: MongoDbFactory, context: MongoMappingContext): MongoTemplate {
        val converter = MappingMongoConverter(DefaultDbRefResolver(mongoDbFactory), context)
        converter.typeMapper = DefaultMongoTypeMapper(null)

        return MongoTemplate(mongoDbFactory, converter)

    }
}
