package org.acme.rest.Service

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import org.acme.rest.model.UserDto
import org.acme.rest.repository.UserRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.validation.ValidationException

@ApplicationScoped
class UserServiceImpl : UserService {

    @Inject private lateinit var userRepository: UserRepository

    override fun findOne(id: String): UserDto? = userRepository.findOne(id)
            ?.let { d -> UserDto(d) }

    override fun findAll(): List<UserDto> = userRepository.findAll()
            ?.map { it -> UserDto(it) }
            ?.toCollection(arrayListOf())
            ?: emptyList()

    override fun create(userDto: UserDto): UserDto = userDto.toDocument()
            ?.let { d -> userRepository.create(d) }
            ?.let { it -> UserDto(it) }
            ?: throw ValidationException()

    override fun update(id: String, user: UserDto): UpdateResult? = user.toDocument()
            ?.let { d -> userRepository.update(id, d) }

    override fun delete(id: String): DeleteResult? = userRepository.delete(id)
}
