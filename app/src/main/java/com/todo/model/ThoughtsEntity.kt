package com.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("thoughts")
data class ThoughtsEntity(
    @PrimaryKey val id: UUID,
    val content: String,
    val createdAt: String
)
