package com.plusmobileapps.backend

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf
import tutorial.dog
import tutorial.dogsResult

@OptIn(ExperimentalSerializationApi::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            serialization(ContentType.Application.ProtoBuf, ProtoBuf.Default)
        }

        install(Routing) {
            get("/dogs") {
                delay(1000L) // artificial delay so loading can happen on the client
                call.respondBytes(dogsResult {
                    dogs.add(dog {
                        id = 1
                        breedName = "Doodles"
                    })
                    dogs.add(dog {
                        id = 2
                        breedName = "Pugs"
                    })
                    dogs.add(dog {
                        id = 3
                        breedName = "Corgi"
                    })
                }.toByteArray(), ContentType.Application.ProtoBuf)
            }
        }

    }.start(wait = true)
}
