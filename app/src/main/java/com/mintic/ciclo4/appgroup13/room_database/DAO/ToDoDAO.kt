package com.mintic.ciclo4.appgroup13.room_database.DAO

import androidx.room.*
import com.mintic.ciclo4.appgroup13.room_database.entity.ToDo

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM ToDo")
    suspend fun getAllTasks(): List<ToDo>

    @Query("SELECT * FROM ToDo WHERE id = :id")
    suspend fun findById(id : Int): ToDo

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: ToDo) : Long

    @Update
    suspend fun updateTask(task: ToDo)

    @Delete
    suspend fun deleteTask(task: ToDo)
}

