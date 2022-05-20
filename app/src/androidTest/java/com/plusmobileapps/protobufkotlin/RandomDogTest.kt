package com.plusmobileapps.protobufkotlin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.plusmobileapps.protobufkotlin.di.ServerConfig
import com.plusmobileapps.protobufkotlin.di.ServerConfigModule
import dagger.Binds
import dagger.Module
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServerConfigModule::class]
)
abstract class ServerConfigModule {

    @Singleton
    @Binds
    abstract fun bindServerConfig(
        mockServerConfig: MockServerConfig
    ): ServerConfig
}

@HiltAndroidTest
@UninstallModules(ServerConfigModule::class)
class RandomDogTest: MockServerTest() {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun randomDogLoadsOnScreen() {
        composeTestRule.onNodeWithText("Refresh").performClick()
        Thread.sleep(4000)
        composeTestRule.onNodeWithText(dog.breedName).assertIsDisplayed()
    }
}