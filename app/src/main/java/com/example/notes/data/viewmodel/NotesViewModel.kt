package com.example.notes.data.viewmodel

import android.app.Application
import android.app.DownloadManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesDatabase
import com.example.notes.data.model.NotesData
import com.example.notes.data.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val notesDao = NotesDatabase.getDatabase(application).notesDao()
    private val repository: NotesRepository

    val getAllData: LiveData<List<NotesData>>
    val sortByHighPriority: LiveData<List<NotesData>>
    val sortByLowPriority: LiveData<List<NotesData>>

    init {
        repository = NotesRepository(notesDao)
        getAllData = repository.getAllData
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority
    }

    fun insertData(notesData: NotesData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(notesData)
        }
    }

    fun updateData(notesData: NotesData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(notesData)
        }
    }

    fun deleteData(notesData: NotesData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(notesData)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<NotesData>> {
        return repository.searchDatabase(searchQuery)
    }
}