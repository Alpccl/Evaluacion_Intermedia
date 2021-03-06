package com.example.evaluacionintermedia.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    //Insertar Elementos

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(Task : TaskEntity)

    //Insertar Lista de Tareas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertAllTask(listTask : List<TaskEntity>)

    //Actualizar elementos
    @Update
    suspend fun updateTask(task : TaskEntity)

    //Borrar una tarea
    @Delete
    suspend fun deleteTask(task: TaskEntity)

    //Borrar todo
    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    //Traer Todos los elementos
    @Query("SELECT * FROM task_table")
    fun getAllTask() : LiveData<List<TaskEntity>>

    //traer por Titulo y limita resultado a 1
    @Query("SELECT * FROM task_table WHERE nombre = nombre LIMIT 1")
    fun getTaskByTitle(nombre : String): LiveData<TaskEntity>


    //traer por id
    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskById(id: Int): LiveData<TaskEntity>

}