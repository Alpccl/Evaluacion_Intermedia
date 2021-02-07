package com.example.evaluacionintermedia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity (tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int = 0,
    val nombre: String,
    val precio: Int,
    val cantidad: Int
) {

}