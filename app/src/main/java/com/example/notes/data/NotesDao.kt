package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.model.NotesData

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<NotesData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(notesData: NotesData)

    @Update
    suspend fun updateData(notesData: NotesData)

    @Delete
    suspend fun  deleteData(notesData: NotesData)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM notes_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<NotesData>>

    @Query("SELECT * FROM notes_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<NotesData>>

    @Query("SELECT * FROM notes_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<NotesData>>

}