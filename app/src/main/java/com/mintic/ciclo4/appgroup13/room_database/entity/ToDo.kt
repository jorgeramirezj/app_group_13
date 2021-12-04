package com.mintic.ciclo4.appgroup13.room_database.entity

import androidx.room.*

@Entity
data class ToDo (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val time : String,
    val place : String
)