package com.hornet.controller

import com.hornet.mappers.toDto
import com.hornet.model.Category
import com.hornet.model.Category.category_type
import com.hornet.model.Product
import com.hornet.model.Product.product_capacity
import com.hornet.model.Product.product_file_image_name
import com.hornet.model.Product.product_mark
import com.hornet.model.Product.product_name
import com.hornet.model.Units
import com.hornet.model.Units.unit_type
import com.hornet.repos.MinioRepository
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.productRoutes(
    minioRepository: MinioRepository,
) {
    routing {
        get("/product/categories") {
            try {
                val categories = transaction {
                    Category
                        .slice(category_type)
                        .selectAll()
                        .map { it[category_type] }
                }
                call.respond(listOf("Все") + categories)
            }catch (e:Exception){
                call.respondText(
                    e.toString(),
                    status = HttpStatusCode.InternalServerError,
                    contentType = ContentType.Text.Plain
                )
            }
        }
        get("/product/all") {
            try {
                val product = transaction {
                    Product
                        .innerJoin(Units)
                        .slice(
                            product_mark,
                            product_name,
                            unit_type,
                            product_capacity,
                            product_file_image_name,
                        )
                        .selectAll()
                        .toList()
                }
                val result = product.map { p ->
                    val url = minioRepository.getUrlPicture(
                        bucketName = environment.config.property("s3.bucket_products").getString(),
                        objectName = p[product_file_image_name]
                    )
                    p.toDto(url)
                }
                call.respond(result)
            }catch (e:Exception){
                call.respondText(e.localizedMessage, ContentType.Text.Plain)
            }
        }
        get("/product/{category}") {
            val category = call.parameters["category"]
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            try {
                val product = transaction {
                    Product
                        .innerJoin(Category)
                        .innerJoin(Units)
                        .slice(
                            product_mark,
                            product_name,
                            unit_type,
                            product_capacity,
                            product_file_image_name,
                        )
                        .select { category_type eq category }
                        .toList()
                }
                val result = product.map { p ->
                    val url = minioRepository.getUrlPicture(
                        bucketName = environment.config.property("s3.bucket_products").getString(),
                        objectName = p[product_file_image_name]
                    )
                    p.toDto(url)
                }
                call.respond(result)
            }catch (e:Exception){
                call.respondText(e.localizedMessage, ContentType.Text.Plain)
            }
        }
        get("/product/{username}/{password}") {
            val username = call.parameters["username"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)
            val password = call.parameters["password"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)

            val user = transaction {

            }
        }
    }
}
