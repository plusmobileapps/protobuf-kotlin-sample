package com.plusmobileapps.backend

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import tutorial.dog
import tutorial.dogsResult

@Serializable
data class RandomDogResponse(val message: String, val status: String)

fun Route.randomDogRoute(client: HttpClient)  {
    get("/random-dog") {
        val apiDog: RandomDogResponse = client.get("https://dog.ceo/api/breeds/image/random").body()
        call.respondBytes(dogsResult {
            dogs.add(dog {
                id = 1
                breedName = apiDog.message
            })
        }.toByteArray(), ContentType.Application.ProtoBuf)
    }
}