package com.plusmobileapps.backend

import com.plusmobileapps.model.dog
import com.plusmobileapps.model.dogsResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class RandomDogResponse(val message: String, val status: String) {
    /**
     * https://images.dog.ceo/breeds/waterdog-spanish/20180723_185544.jpg
     */
    val breedName: String =
        message.removePrefix("https://images.dog.ceo/breeds/").split("/").first()
}

fun Route.randomDogRoute(client: HttpClient) {
    get("/random-dog") {
        val apiDog: RandomDogResponse = client.get("https://dog.ceo/api/breeds/image/random").body()
        call.respondBytes(dogsResult {
            dogs.add(dog {
                id = 1
                breedName = apiDog.breedName
                imageUrl = apiDog.message
            })
        }.toByteArray(), ContentType.Application.ProtoBuf)
    }
}