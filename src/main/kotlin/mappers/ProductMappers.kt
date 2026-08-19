package com.hornet.mappers

import com.hornet.dto.ProductDto
import com.hornet.model.Product
import com.hornet.model.Units
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDto(url: String) = ProductDto(
    product_mark = this[Product.product_mark],
    product_name = this[Product.product_name],
    product_units = this[Units.unit_type],
    product_capacity = this[Product.product_capacity],
    product_image_link = url
)