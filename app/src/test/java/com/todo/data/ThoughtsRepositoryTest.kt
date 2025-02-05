package com.todo.data

import com.todo.model.ThoughtsEntity
import com.todo.utils.FakeDao
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

class ThoughtsRepositoryTest {

    private val fakeDao = FakeDao()
    private val thoughtsRepo = ThoughtsRepository(fakeDao)


    @Test
    fun shouldBe_able_to_getAll_thoughts() = runTest {
        withTimeoutOrNull(10) {
            thoughtsRepo.getThoughts().collect { result ->
                assertEquals(result, FakeDao.ExpectedResult)
            }
        }
    }

    @Test
    fun shouldBe_able_to_find_thoughts() = runTest {
        withTimeoutOrNull(10) {
            thoughtsRepo.findThought(UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9")).collect { result ->
                assertEquals(result,          ThoughtsEntity(
                    UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9"),
                    "ContentOne",
                    "2025-01-29T19:25:36.145562029Z"
                ))
            }
        }
    }

}