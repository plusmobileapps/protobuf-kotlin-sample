package com.plusmobileapps.protobufkotlin.network

import retrofit2.http.GET
import tutorial.DogOuterClass.DogsResult

interface DogService {

    @GET("/dogs")
    suspend fun getDogs(): DogsResult

}