package com.plusmobileapps.backend

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf

@OptIn(ExperimentalSerializationApi::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            serialization(ContentType.Application.ProtoBuf, ProtoBuf.Default)
        }

        val client = HttpClient(CIO) {
            install(io.ktor.client.plugins.ContentNegotiation) {
                json(Json {
                    isLenient = true
                    prettyPrint = true
                })
            }
        }

        install(Routing) {
            randomDogRoute(client)
        }

    }.start(wait = true)
}
