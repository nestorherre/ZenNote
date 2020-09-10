package com.nhapps.zennote.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhapps.zennote.model.entity.Note
import com.nhapps.zennote.model.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NoteViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        viewModel = NoteViewModel(FakeNoteRepository(),testScope)
    }

    @After
    fun finish(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `return false if note title is empty`(){
        val note = Note("", "some text", "some date")
        val titleNotEmpty = viewModel.canSaveNote(note)
        assertThat(titleNotEmpty).isFalse()
    }

    @Test
    fun `return true if note title is not empty`(){
        val note = Note("title", "some text", "some date")
        val titleNotEmpty = viewModel.canSaveNote(note)
        assertThat(titleNotEmpty).isTrue()
    }

    @Test
    fun `add ID to note if it exists`(){
        val note = Note("title", "some text", "some date")
        val noteID = "1"
        note.id = viewModel.addNoteID(noteID)
        assertThat(note.id).isEqualTo(noteID.toInt())
    }

    @Test
    fun `note has nullID if the ID is null`(){
        val note = Note("title", "some text", "some date")
        val noteID = null
        note.id = viewModel.addNoteID(noteID)
        assertThat(note.id).isNull()
    }

    @Test
    fun `does not insert note if title is empty`(){
        val note = Note("", "some text", "some date")
        viewModel.insert(note, "1")
        val insertedNote = viewModel.noteSaved.value
        assertThat(insertedNote).isNull()
    }

    @Test
    fun `insert note if title is not empty`(){
        val note = Note("some title", "some text", "some date")
        viewModel.insert(note, "1")
        val insertedNote = viewModel.noteSaved.value
        assertThat(insertedNote).isTrue()
    }

    @Test
    fun `delete note returns null`(){
        val note = Note("some title", "some text", "some date")
        viewModel.insert(note, "1")
        viewModel.delete(note)
        val repoNote = viewModel.getNote(1)
        assertThat(repoNote).isNull()
    }


}