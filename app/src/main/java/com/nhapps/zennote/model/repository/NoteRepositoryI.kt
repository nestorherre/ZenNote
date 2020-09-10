package com.nhapps.zennote.model.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import com.nhapps.zennote.model.AppDatabase
import com.nhapps.zennote.model.entity.Note

interface NoteRepositoryI {

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    fun getNote(id: Int): Note?

    fun getAllNotes(): LiveData<List<Note>>

}