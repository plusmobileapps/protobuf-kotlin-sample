package com.plusmobileapps.protobufkotlin.network

import retrofit2.http.GET
import com.plusmobileapps.model.DogOuterClass.DogsResult

interface DogService {

    companion object {
        const val RANDOM_DOG_ROUTE = "/random-dog"
    }

    @GET(RANDOM_DOG_ROUTE)
    suspend fun getDogs(): DogsResult

}