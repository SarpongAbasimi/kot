package com.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.todo.dao.ThoughtsDao
import com.todo.model.ThoughtsEntity

@Database(entities = [ThoughtsEntity::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase(){
    abstract fun thoughtsDao(): ThoughtsDao

    companion object {
        @Volatile
       private var instance: AppDB? = null

        fun create(context: Context, dbName: String): AppDB {
            return instance ?: synchronized(this){
                Room.databaseBuilder(context, AppDB::class.java, dbName)
                    .build()
                    .also { instance = it }
            }
        }
    }
}