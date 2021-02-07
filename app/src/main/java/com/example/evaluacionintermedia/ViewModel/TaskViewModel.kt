package com.example.evaluacionintermedia.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.evaluacionintermedia.model.TaskDataBase
import com.example.evaluacionintermedia.model.TaskEntity
import com.example.evaluacionintermedia.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):
    AndroidViewModel(application) {

        //variable que representa al repositorio

        private val repository: TaskRepository


    //LiveData expone info

        val allTask : LiveData<List<TaskEntity>>

        init{
            val TaskDao = TaskDataBase.getDataBase(application).getTaskDao()
            repository = TaskRepository(TaskDao)
            allTask = repository.listAllTask
        }
    fun insertTask(task : TaskEntity) = viewModelScope.launch {
        repository.insertTask(task)
    }
    fun updateTask(task : TaskEntity) = viewModelScope.launch {
        repository.updateTask(task)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun getTaskById(id : Int): LiveData<TaskEntity>{
        return repository.getTaskById(id)
    }

}