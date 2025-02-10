package com.todo.ui

import com.todo.model.ThoughtsEntity
import com.todo.ui.addScreen.AddThoughtsViewModel
import com.todo.utils.FakeRepo
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID


class AddThoughtsViewModelTest {

    private val fakeRepo = FakeRepo()
    private val addThoughtsViewModel = AddThoughtsViewModel(fakeRepo)

    @Test
    fun shouldBeAbleToUpdateUiState() {
        val initialState = addThoughtsViewModel.uiState.value.content
        addThoughtsViewModel.updateState("updated state")
        val currentState = addThoughtsViewModel.uiState.value.content

        assertEquals(initialState, "")
        assertEquals(currentState, "updated state")
    }

    @Test
    fun shouldBeAbleToInsertAThought() = runTest{
        val id = "00f057c6-2552-46be-a1ac-d73cbbc480f9"
        addThoughtsViewModel.create(
            UUID.fromString(id)
        )

        withTimeoutOrNull(10){
            fakeRepo.findThought(UUID.fromString(id)).collect { thought ->
                assertEquals(thought, ThoughtsEntity(
                    UUID.fromString(id),
                    "ContentOne",
                    "2025-01-29T19:25:36.145562029Z"
                ))
            }
        }
    }
}