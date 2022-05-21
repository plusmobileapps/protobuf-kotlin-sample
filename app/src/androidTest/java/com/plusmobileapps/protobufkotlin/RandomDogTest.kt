package com.plusmobileapps.protobufkotlin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
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