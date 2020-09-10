package com.nhapps.zennote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nhapps.zennote.model.entity.Note
import com.nhapps.zennote.model.repository.NoteRepository
import com.nhapps.zennote.model.repository.NoteRepositoryI

class BrowseNotesViewModel(private val repository: NoteRepositoryI): ViewModel() {

    fun getAllNotes(): LiveData<List<Note>> {
        return repository.getAllNotes()
    }

}