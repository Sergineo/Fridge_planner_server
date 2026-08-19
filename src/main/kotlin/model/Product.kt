package com.hornet.model

import org.jetbrains.exposed.sql.Table

object Product: Table() {
    val id_product = integer("id_product").autoIncrement()
    val product_mark = varchar("product_mark", 100)
    val product_name = varchar("product_name", 100)
    val product_category = reference("product_category", Category.id_category)
    val product_units = reference("product_units", Units.id_units)
    val product_capacity = integer("product_capacity")
    val product_file_image_name = varchar("product_file_image_name", 100)
    override val primaryKey = PrimaryKey(id_product)
}
