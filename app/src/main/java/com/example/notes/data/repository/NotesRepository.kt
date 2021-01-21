package com.example.notes.data.repository

import androidx.lifecycle.LiveData
import com.example.notes.data.NotesDao
import com.example.notes.data.model.NotesData

class NotesRepository(private val notesDao: NotesDao) {

    val getAllData: LiveData<List<NotesData>> = notesDao.getAllData()
    val sortByHighPriority: LiveData<List<NotesData>> = notesDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<NotesData>> = notesDao.sortByLowPriority()

    suspend fun insertData(notesData: NotesData) {
        notesDao.insertData(notesData)
    }

    suspend fun updateData(notesData: NotesData) {
        notesDao.updateData(notesData)
    }

    suspend fun deleteData(notesData: NotesData) {
        notesDao.deleteData(notesData)
    }

    suspend fun deleteAllData() {
        notesDao.deleteAllData()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<NotesData>> {
        return notesDao.searchDatabase(searchQuery)
    }
}