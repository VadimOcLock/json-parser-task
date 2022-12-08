package com.example.plugins

import com.example.controllers.FuncLocController
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val controller = FuncLocController()

    routing {
        static("/static") {
            resources("files")
        }
        get("/") {
            controller.callBranch(call)
        }
        get("/v1/tree") {
            var id = call.parameters["id"]
            println("${call.request.path()}?id=$id")

            controller.getParentBranches(call)
        }
    }
}
