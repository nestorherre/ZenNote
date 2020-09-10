package com.nhapps.zennote.di

import com.nhapps.zennote.model.AppDatabase
import com.nhapps.zennote.model.repository.NoteRepository
import com.nhapps.zennote.model.repository.NoteRepositoryI
import com.nhapps.zennote.viewmodel.BrowseNotesViewModel
import com.nhapps.zennote.viewmodel.BrowseNotesViewModelFactory
import com.nhapps.zennote.viewmodel.NoteViewModel
import com.nhapps.zennote.viewmodel.NoteViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    single<NoteRepositoryI> { NoteRepository(get())}
    single { NoteViewModelFactory(get())}
    single { BrowseNotesViewModelFactory(get()) }
    viewModel { NoteViewModel(get()) }
    viewModel { BrowseNotesViewModel(get()) }
}