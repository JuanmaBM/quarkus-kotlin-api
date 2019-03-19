package org.acme.rest.repository

import com.mongodb.client.FindIterable
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import org.acme.rest.model.UserDto
import org.bson.Document

interface UserRepository {
    fun findAll(): FindIterable<Document>?
    fun create(document: Document): Document?
    fun findOne(id: String): Document?
    fun delete(id: String): DeleteResult?
    fun update(id: String, user: Document): UpdateResult?
}
