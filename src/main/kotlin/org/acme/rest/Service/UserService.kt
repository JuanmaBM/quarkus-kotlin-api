package org.acme.rest.Service

import com.mongodb.client.FindIterable
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import org.acme.rest.model.UserDto
import org.bson.Document

interface UserService {
    fun findAll(): List<UserDto>
    fun create(userDto: UserDto): UserDto
    fun delete(id: String): DeleteResult?
    fun update(id: String, user: UserDto): UpdateResult?
    fun findOne(id: String): UserDto?
}
