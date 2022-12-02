package com.example.plugins

import com.example.models.ItemCollection
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.net.URL

fun Application.configureRouting() {

    routing {
        static("/static") {
            resources("files")
        }
        get("/jsonRead") {
            URL("http://127.0.0.1:8080/static/branch.json").openStream().use {
                ItemCollection = Json.decodeFromStream(it)
                call.respondText("Read from JSON was successful", status = HttpStatusCode.Created)
            }
        }
        get("/items") {
            call.respond(FreeMarkerContent("index.ftl", mapOf("ItemCollection" to ItemCollection)))
        }
    }
}
