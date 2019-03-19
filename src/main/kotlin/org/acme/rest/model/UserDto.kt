package org.acme.rest.model

import org.bson.Document

class UserDto(var id: String?, var name: String, var age: Int) {

    constructor() : this(null, "", 0)
    constructor(document: Document) : this(document.getObjectId("_id")?.toString() ?: "",
            document.getString("name"), document.getInteger("age"))

    fun toDocument() = Document("_id", this.id).append("name", this.name).append("age", this.age)
}
