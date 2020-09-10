package com.nhapps.zennote.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nhapps.zennote.model.entity.Note

class FakeNoteRepository:  NoteRepositoryI {

    private val noteList = mutableListOf<Note>()
    private val observableNoteList = MutableLiveData<List<Note>>(noteList)

    override suspend fun  insert(note: Note){
        noteList.add(note)
    }

    override suspend fun delete(note: Note){
        noteList.remove(note)
    }

    override fun getNote(id: Int): Note? {
        try {
            return noteList.get(id)
        }catch(e: Exception) {
            return null
        }
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        return observableNoteList
    }

}