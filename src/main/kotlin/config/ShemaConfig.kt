package com.hornet.config

import com.hornet.model.Category
import com.hornet.model.Product
import com.hornet.model.Units
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun configureShema() {
    transaction {
        addLogger(StdOutSqlLogger)

        //Изменение стуктуры базы данных

//        SchemaUtils.drop(Product, Category, Units)
//        SchemaUtils.create(Product, Category, Units)

        commit()
    }
}