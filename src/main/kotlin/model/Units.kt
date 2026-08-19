package com.hornet.model

import com.hornet.model.Category.id_category
import org.jetbrains.exposed.sql.Table

object Units: Table() {
    val id_units = integer("id_units").autoIncrement()
    val unit_type = varchar("unit_type", 10)
    override val primaryKey = PrimaryKey(id_units)
}