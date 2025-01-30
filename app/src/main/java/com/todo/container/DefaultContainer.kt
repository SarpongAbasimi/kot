package com.todo.container

import android.content.Context
import com.todo.data.ThoughtsRepository
import com.todo.db.AppDB


class DefaultContainer(context: Context, dbName: String){
    private val thoughtsDao = AppDB.create(context, dbName).thoughtsDao()

    val repository: ThoughtsRepository by lazy {
        ThoughtsRepository(thoughtsDao)
    }
}