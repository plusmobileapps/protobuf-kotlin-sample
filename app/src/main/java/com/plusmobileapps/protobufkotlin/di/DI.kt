package com.plusmobileapps.protobufkotlin.di

import com.plusmobileapps.protobufkotlin.network.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.protobuf.ProtoConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(ProtoConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideDogService(retrofit: Retrofit): DogService = retrofit.create(DogService::class.java)

}