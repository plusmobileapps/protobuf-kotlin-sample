package com.plusmobileapps.protobufkotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plusmobileapps.model.dog
import com.plusmobileapps.model.dogsResult
import com.plusmobileapps.protobufkotlin.di.ServerConfig
import com.plusmobileapps.protobufkotlin.di.ServerConfigModule
import com.plusmobileapps.protobufkotlin.network.DogService
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

class MockServerConfig(_port: Int = 8080) : ServerConfig {
    override val scheme: String = "http"
    override val host: String = "localhost"
    override val port: Int? = _port
}

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
abstract class MockServerTest {

    private lateinit var server: MockWebServer

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    private val port = 8080

    val dog = dog {
        id = 1
        breedName = "floofy"
        imageUrl = "some image url"
    }

    @Before
    fun setUp() {
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    DogService.RANDOM_DOG_ROUTE -> MockResponse().setResponseCode(200).setBody(
                        Buffer().write(dogsResult {
                            dogs.add(dog)
                        }.toByteArray())
                    )
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }
        server = MockWebServer()
        server.dispatcher = dispatcher
        server.start(port)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}