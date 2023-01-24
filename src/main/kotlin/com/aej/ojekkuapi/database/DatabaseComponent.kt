package com.aej.ojekkuapi.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {

    val database: MongoClient = KMongo.createClient(DATABASE_URL)

    companion object {
        private const val DATABASE_URL = "mongodb+srv://aej:1234@cluster0.68kdwrd.mongodb.net/?retryWrites=true&w=majority"
        const val DATABASE_NAME = "ojekku"
    }
}