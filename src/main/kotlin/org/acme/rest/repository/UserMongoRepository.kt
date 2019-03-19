package org.acme.rest.repository

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import org.acme.rest.model.UserDto
import org.bson.Document
import org.bson.types.ObjectId
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class UserMongoRepository : UserRepository {

    @Inject
    @ConfigProperty(name = "mongo.db.name")
    private lateinit var databaseName: String

    @Inject
    @ConfigProperty(name = "mongo.db.collection.name")
    private lateinit var collectionName: String

    @Inject private lateinit var mongoClient: MongoClient
    private lateinit var collection: MongoCollection<Document>

    @PostConstruct
    fun init() {
        collection = mongoClient.getDatabase(databaseName).getCollection(collectionName)
    }

    override fun findAll(): FindIterable<Document>? = collection.find()

    override fun delete(id: String): DeleteResult? = Filters.eq("_id", ObjectId(id))
            .let { f -> collection.deleteOne(f) }

    override fun findOne(id: String): Document? = Filters.eq("_id", ObjectId(id))
            .let { f -> collection.find(f) }.first()

    override fun update(id: String, user: Document): UpdateResult? = Filters.eq("_id", ObjectId(id))
            .let { f -> collection.replaceOne(f, user) }

    override fun create(document: Document): Document? = document.append("_id", ObjectId())
            ?.apply { collection.insertOne(document) }
}
