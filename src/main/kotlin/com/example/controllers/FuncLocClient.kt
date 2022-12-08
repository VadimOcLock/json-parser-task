package com.example.controllers

import com.example.models.FuncLoc
import com.example.models.FuncLocApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FuncLocClient(
    private val client: HttpClient = HttpClient(CIO) {
    install(Resources)
    install(ContentNegotiation) { jackson() }

    defaultRequest {
        host = "127.0.0.1";
        port = 8081;
        url{
            protocol = URLProtocol.HTTP
        }
    }
}, private val logger: Logger = LoggerFactory.getLogger("FuncLocClient"))
{
    suspend fun getFuncLocBranch(id: String): List<FuncLoc> {
        val url = FuncLocApi.FuncLoc.Branch(id = id)
        val httpResponse = client.get(url)
        val childs : List<FuncLoc> = httpResponse.body()

        return childs
    }
}