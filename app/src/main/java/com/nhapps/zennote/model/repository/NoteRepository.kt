package com.nhapps.zennote.model.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import com.nhapps.zennote.model.AppDatabase
import com.nhapps.zennote.model.entity.Note

class NoteRepository(private val database: AppDatabase): NoteRepositoryI {


    override suspend fun insert(note: Note){
        database.getNoteDao().insert(note)
    }

    override suspend fun delete(note: Note){
        database.getNoteDao().delete(note)
    }

    override fun getNote(id: Int): Note? {
        return database.getNoteDao().getNote(id)
    }

    override fun getAllNotes(): LiveData<List<Note>>{
        return database.getNoteDao().getAllNotes()
    }

}