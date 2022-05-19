package com.plusmobileapps.protobufkotlin.network

import retrofit2.http.GET
import com.plusmobileapps.model.DogOuterClass.DogsResult

interface DogService {

    @GET("/random-dog")
    suspend fun getDogs(): DogsResult

}