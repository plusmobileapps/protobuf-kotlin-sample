package com.plusmobileapps.protobufkotlin.di

import com.plusmobileapps.protobufkotlin.network.DogService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.protobuf.ProtoConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

interface ServerConfig {
    val scheme: String
    val host: String
    val port: Int?

    fun baseUrl(): String = with(StringBuilder()) {
        append("$scheme://$host")
        port?.let { append(":$it") }
        toString()
    }
}

@Singleton
class ServerConfigImpl @Inject constructor() : ServerConfig {
    override val scheme: String = "http"
    override val host: String = "10.0.2.2"
    override val port: Int? = 8080
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ServerConfigModule {

    @Binds
    abstract fun bindServerConfig(serverConfig: ServerConfigImpl): ServerConfig
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(serverConfig: ServerConfig): Retrofit = Retrofit.Builder()
        .baseUrl(serverConfig.baseUrl())
        .addConverterFactory(ProtoConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideDogService(retrofit: Retrofit): DogService = retrofit.create(DogService::class.java)
}