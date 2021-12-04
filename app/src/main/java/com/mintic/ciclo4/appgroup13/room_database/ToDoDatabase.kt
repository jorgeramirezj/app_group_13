package com.mintic.ciclo4.appgroup13.room_database

import androidx.room.*
import com.mintic.ciclo4.appgroup13.room_database.DAO.*
import com.mintic.ciclo4.appgroup13.room_database.entity.*

@Database(
    entities = arrayOf(
        ToDo::class
    ),
    version = 1
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun todoDao() : ToDoDAO
}