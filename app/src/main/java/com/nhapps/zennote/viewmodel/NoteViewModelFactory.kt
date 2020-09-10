package com.nhapps.zennote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nhapps.zennote.model.repository.NoteRepository
import com.nhapps.zennote.model.repository.NoteRepositoryI
import kotlinx.coroutines.CoroutineScope

class NoteViewModelFactory(private val repository: NoteRepositoryI): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T
    }
}