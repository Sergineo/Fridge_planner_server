package com.hornet.model

import com.hornet.model.Product.id_product
import org.jetbrains.exposed.sql.Table

object Category: Table() {
    val id_category = integer("id_category").autoIncrement()
    val category_type = varchar("category_type", 50)
    override val primaryKey = PrimaryKey(id_category)
}