package com.todo.utils

import com.todo.dao.ThoughtsDao
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class FakeDao : ThoughtsDao {
    override suspend fun insert(thoughts: ThoughtsEntity) {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<ThoughtsEntity>> {
        return flowOf<List<ThoughtsEntity>>(
            ExpectedResult
        )
    }

    override suspend fun delete(thoughts: ThoughtsEntity) {
        TODO("Not yet implemented")
    }

    override fun find(id: UUID): Flow<ThoughtsEntity> {
        return flowOf<ThoughtsEntity>(
            ThoughtsEntity(
                UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9"),
                "ContentOne",
                "2025-01-29T19:25:36.145562029Z"
            )
        )
    }

    override fun update(thoughts: ThoughtsEntity) {
        ThoughtsEntity(
            UUID.fromString("00f057c6-2552-46be-a1ac-d73cbbc480f9"),
            thoughts.content,
            "2025-01-29T19:25:36.145562029Z"
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