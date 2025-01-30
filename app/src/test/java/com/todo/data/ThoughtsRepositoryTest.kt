package com.todo.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.todo.dao.ThoughtsDao
import com.todo.db.AppDB
import com.todo.model.ThoughtsEntity
import org.junit.Test
import java.util.UUID
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ThoughtsRepositoryTest {

    private lateinit var thoughtsDao: ThoughtsDao
    private lateinit var db: AppDB

//    private val appDB: AppDB = Room.inMemoryDatabaseBuilder(
//        ApplicationProvider.getApplicationContext(),
//        AppDB::class.java
//    ).build()

//    private val dao = appDB.thoughtsDao()
//    private val thoughtsRepo: ThoughtsRepo = ThoughtsRepository(thoughtsDao)


    @Before
    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
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
        thoughtsRepo.findThought(id).collect { result ->
            assertEquals(6, 2 + 2)
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