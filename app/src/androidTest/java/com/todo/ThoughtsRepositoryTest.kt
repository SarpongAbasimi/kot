package com.todo

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.todo.dao.ThoughtsDao
import com.todo.data.ThoughtsRepo
import com.todo.data.ThoughtsRepository
import com.todo.db.AppDB
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ThoughtsRepositoryTest {

    private lateinit var thoughtsDao: ThoughtsDao
    private lateinit var db: AppDB


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDB::class.java
        ).build()
        thoughtsDao = db.thoughtsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun shouldBe_able_to_insert_thoughts() = runTest {
        val thoughtsRepo: ThoughtsRepo = ThoughtsRepository(thoughtsDao)
        val id = UUID.fromString("350bea47-d383-4d2b-8f6a-3a19b3c8b5c1")
        val entity: ThoughtsEntity = entityBuilder(id, "I am thinking of learning to sing")

        thoughtsRepo.insertThoughts(entity)

       runBlocking {
            withTimeoutOrNull(10){
                thoughtsRepo.findThought(id).collect{result ->
                    assertEquals(result, entity)
                }
            }
        }
    }

    @Test
    fun shouldBe_able_to_delete_thoughts() = runTest {
        val thoughtsRepo: ThoughtsRepo = ThoughtsRepository(thoughtsDao)
        val id = UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9")
        val entity: ThoughtsEntity = entityBuilder(id, "I am thinking of singing")

        thoughtsRepo.insertThoughts(entity)
        thoughtsRepo.deleteThough(entity)

        runBlocking {
            withTimeoutOrNull(10){
                val result = thoughtsRepo.findThought(id).toList()
                assert(result.isEmpty())
            }
        }
    }

    @Test
    fun shouldBe_able_to_get_all_thoughts() = runTest {
        val thoughtsRepo: ThoughtsRepo = ThoughtsRepository(thoughtsDao)

        val idOne = UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9")
        val idTwo = UUID.fromString("1535fb83-d777-468c-9d74-c351a57a3b83")

        val entityOne: ThoughtsEntity = entityBuilder(idOne, "I am thinking of singing")
        val entityTwo: ThoughtsEntity = entityBuilder(idTwo, "I am thinking painting")

        thoughtsRepo.insertThoughts(entityOne)
        thoughtsRepo.insertThoughts(entityTwo)


        runBlocking {
            withTimeoutOrNull(10){
               thoughtsRepo.getThoughts().collect { result ->
                    assertEquals(result.toSet(), listOf(entityOne, entityTwo).toSet())
                }
            }
        }
    }


    private fun entityBuilder(uuid: UUID, content: String): ThoughtsEntity{
        return ThoughtsEntity(
            uuid,
            content,
            "2025-01-29T19:25:36.145562029Z"
        )
    }
}