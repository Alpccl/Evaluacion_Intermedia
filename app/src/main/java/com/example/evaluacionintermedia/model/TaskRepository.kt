package com.example.evaluacionintermedia.model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao : TaskDao) {

    //En esto contiene toda la info de la base de datos

    val listAllTask : LiveData<List<TaskEntity>> = taskDao.getAllTask()

    suspend fun insertTask(task : TaskEntity){
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: TaskEntity){
        taskDao.updateTask(task)
    }

    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
    suspend fun deleteTask(task : TaskEntity){
        taskDao.deleteTask(task)
    }
    fun getTaskById(id : Int): LiveData<TaskEntity>{
        return taskDao.getTaskById(id)
    }
}