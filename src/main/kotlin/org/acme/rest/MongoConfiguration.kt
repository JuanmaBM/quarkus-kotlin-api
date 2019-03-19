package org.acme.rest

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.inject.Inject

@Configuration
class MongoDBConfiguration {

    @Inject
    @ConfigProperty(name = "mongo.host")
    private lateinit var host: String

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create(host)

}
