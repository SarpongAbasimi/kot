package com.todo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ThoughtsDao {
    @Insert
    suspend fun insert(thoughts: ThoughtsEntity): Unit

    @Query("SELECT * FROM thoughts ORDER BY id DESC")
    fun getAll(): Flow<List<ThoughtsEntity>>

    @Delete
    suspend fun delete(thoughts: ThoughtsEntity): Unit

    @Query("SELECT * FROM thoughts where id = :id")
    fun find(id: UUID): Flow<ThoughtsEntity>

    @Update(entity = ThoughtsEntity::class)
    fun update(thoughts: ThoughtsEntity): Unit
}