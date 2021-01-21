package com.example.notes.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.data.model.Priority
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "notes_table")
@Parcelize
data class NotesData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
    ): Parcelable