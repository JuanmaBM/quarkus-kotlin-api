package org.acme.rest

import org.acme.rest.Service.UserService
import org.acme.rest.model.UserDto
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
class UserController {

    @Inject private lateinit var userService: UserService

    @GET
    fun findAll() = userService.findAll()

    @GET
    @Path("/{id}")
    fun findOne(@PathParam("id") id: String) = userService.findOne(id)
            ?: NotFoundException("User with id $id not found")

    @POST
    fun create(userDto: UserDto): UserDto = userService.create(userDto)

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: String) = userService.delete(id)?.let {  }

    @PUT
    @Path("/{id}")
    fun update(@PathParam("id") id: String, user: UserDto): Unit? = userService.update(id, user)?.let { }
}
