package com.todo

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.data.ThoughtsRepo
import com.todo.model.ThoughtsEntity
import com.todo.ui.addScreen.AddThoughtScreen
import com.todo.ui.addScreen.AddThoughtsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class AddThoughtScreenTest {
    private val fakeRepo = FakeRepo()
    private val viewModel = AddThoughtsViewModel(fakeRepo)
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


class FakeRepo : ThoughtsRepo {
    override suspend fun insertThoughts(thoughtsEntity: ThoughtsEntity) {
        TODO("Not yet implemented")
    }

    override fun getThoughts(): Flow<List<ThoughtsEntity>> {
        return flowOf(ExpectedResult)
    }

    override suspend fun deleteThough(thoughts: ThoughtsEntity) {
        TODO("Not yet implemented")
    }

    override fun findThought(id: UUID): Flow<ThoughtsEntity> {
        return flowOf<ThoughtsEntity>(
            ThoughtsEntity(
                UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9"),
                "ContentOne",
                "2025-01-29T19:25:36.145562029Z"
            )
        )
    }


    companion object {
        val ExpectedResult =  listOf(
            ThoughtsEntity(
                UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9"),
                "ContentOne",
                "2025-01-29T19:25:36.145562029Z"
            ),
            ThoughtsEntity(
                UUID.fromString("01f057c6-2552-46be-a1ac-d73cbbc480f9"),
                "ContentTwo",
                "2025-01-29T19:25:36.145562029Z"
            )
        )
    }

}