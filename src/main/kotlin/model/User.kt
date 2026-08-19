package com.hornet.model

import org.jetbrains.exposed.sql.Table

object User: Table() {
    val id_user = integer("id_user").autoIncrement()
    val avatar_image_link = varchar("avatar_image_link", 100)
    val name = varchar("name", 100)
    val email = varchar("email", 100)
    val group = long("group").nullable()
}