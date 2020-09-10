package com.nhapps.zennote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nhapps.zennote.model.entity.Note
import com.nhapps.zennote.model.repository.NoteRepositoryI
import com.nhapps.zennote.utils.getViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepositoryI,
    private val coroutineScopeProvider: CoroutineScope? = null): ViewModel(){

    private val coroutineScope = getViewModelScope(coroutineScopeProvider)

    private val _emptyTitle = MutableLiveData<Boolean>()
    val emptyTitle: LiveData<Boolean> = _emptyTitle

    private val _noteSaved = MutableLiveData<Boolean>()
    val noteSaved: LiveData<Boolean> = _noteSaved

    fun insert(note: Note, noteID: String?){
        if (canSaveNote(note)){
            _emptyTitle.postValue(false)
            note.id = addNoteID(noteID)
            _noteSaved.postValue(true)
            coroutineScope.launch {
                repository.insert(note)
            }
        }
    }

    fun addNoteID(noteID: String?): Int? {
        if (noteID != null){
            return noteID.toInt()
        }
        return null
    }

    fun canSaveNote(note: Note): Boolean{
        if (note.title == ""){
            _emptyTitle.postValue(true)
            return false
        }
        return true
    }

    fun getNote(id: Int): Note?{
        return repository.getNote(id)
    }

    fun delete(note: Note){
        coroutineScope.launch {
            repository.delete(note)
        }
    }


}