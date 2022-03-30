package com.plusmobileapps.protobufkotlin.di

import com.plusmobileapps.protobufkotlin.network.DogService
import retrofit2.Retrofit
import retrofit2.converter.protobuf.ProtoConverterFactory

object DI {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(ProtoConverterFactory.create())
        .build()

    val dogService: DogService = retrofit.create(DogService::class.java)

}