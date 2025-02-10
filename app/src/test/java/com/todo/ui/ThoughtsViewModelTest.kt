package com.todo.ui

import com.todo.ui.thoughtscreen.ThoughtsViewModel
import com.todo.utils.FakeRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class ThoughtsViewModelTest {

    private val fakeRepo = FakeRepo()
    private val thoughtsViewModel = ThoughtsViewModel(fakeRepo)

    @Test
    fun shouldInitialiseWithAllThoughts() = runTest {

        val thoughts = thoughtsViewModel.uiState.first()

        assertEquals(thoughts, FakeRepo.ExpectedResult)
    }
}