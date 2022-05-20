package com.plusmobileapps.protobufkotlin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.plusmobileapps.protobufkotlin.di.ServerConfig
import com.plusmobileapps.protobufkotlin.di.ServerConfigModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ServerConfigModule::class)
class RandomDogTest: MockServerTest() {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    val mockServerConfig: ServerConfig = MockServerConfig()

    @Test
    fun randomDogLoadsOnScreen() {
        composeTestRule.onNodeWithText("Refresh").performClick()
        Thread.sleep(4000)
        composeTestRule.onNodeWithText(dog.breedName).assertIsDisplayed()
    }
}