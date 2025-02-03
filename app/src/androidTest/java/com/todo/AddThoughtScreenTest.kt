package com.todo

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.ui.addScreen.AddThoughtScreen
import com.todo.ui.addScreen.AddThoughtsViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddThoughtScreenTest {
    private val viewModel = AddThoughtsViewModel()
    private val inputLabel = "What is on your mind? \uD83D\uDCAD"
    private val createButton = "Create"

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun shouldHaveWhatOnYourMindLabel(){
        composeTestRule.setContent {
           AddThoughtScreen(addThoughtsViewModel = viewModel)
        }

        composeTestRule.onNodeWithText(inputLabel)
            .assertExists()
    }

    @Test
    fun shouldAllowValueInTextField(){
        composeTestRule.setContent {
            AddThoughtScreen(addThoughtsViewModel = viewModel)
        }
        val userInput: String = "Thinking of eating"

        composeTestRule
            .onNodeWithText(inputLabel)
            .performTextInput(userInput)

        composeTestRule
            .onNodeWithText(userInput)
            .assertExists()

    }
    
    @Test
    fun shouldHaveACreateButton(){
        composeTestRule.setContent {
            AddThoughtScreen(addThoughtsViewModel = viewModel)
        }

        composeTestRule
            .onNodeWithText(createButton)
            .assertExists()

    }
}