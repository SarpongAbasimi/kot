package com.todo.data

import com.todo.dao.ThoughtsDao
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.util.UUID


interface ThoughtsRepo {
   suspend fun insertThoughts(thoughtsEntity: ThoughtsEntity): Unit
   fun getThoughts(): Flow<List<ThoughtsEntity>>
   suspend fun deleteThough(thoughts: ThoughtsEntity): Unit
   fun findThought(id: UUID): Flow<ThoughtsEntity>
   suspend fun updateThought(thoughts: ThoughtsEntity): Unit
}


class ThoughtsRepository(private val dao: ThoughtsDao): ThoughtsRepo {

    override suspend fun insertThoughts(thoughtsEntity: ThoughtsEntity) {
        try {
            dao.insert(thoughtsEntity)
        } catch (error: Exception) {
            println("An error occurred inserting thoughts $error")
        }
    }

    override fun getThoughts(): Flow<List<ThoughtsEntity>> {
            return dao.getAll()
                .catch {
                    println("An error occurred getting thoughts")
                }

    }

    override suspend fun deleteThough(thoughts: ThoughtsEntity) {
        try {
            dao.delete(thoughts)
        } catch (error: Exception){
            println("An error occurred deleting thoughts")
        }
    }

    override fun findThought(id: UUID): Flow<ThoughtsEntity> {
        return dao.find(id)
            .catch { error ->
                println("Error finding thoughts $error")
            }
    }

    override suspend fun updateThought(thoughts: ThoughtsEntity) {
        try {
            dao.update(thoughts)
        } catch (error: Exception) {
            println("Error whiles updating thought: $error")
        }
    }

}