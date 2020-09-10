package com.nhapps.zennote.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nhapps.zennote.R
import com.nhapps.zennote.model.entity.Note
import com.nhapps.zennote.utils.HideBar
import com.nhapps.zennote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private var noteID: String? = null
    private var noteExists = false
    private val noteViewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        HideBar.apply(this)
        noteExists = checkIfNoteExists()
        switchUIIfNoteExists()
        setListeners()
    }

    private fun switchUIIfNoteExists() {
        if (noteExists){
            btnSaveNote.text = "Edit"
            btnDeleteNote.visibility = View.VISIBLE
            etNoteTitle.setText(intent.getStringExtra("title"))
            etNoteText.setText(intent.getStringExtra("text"))
        }
    }

    private fun checkIfNoteExists(): Boolean {
        noteID = intent.getStringExtra("id")
        if (noteID == null){
            return false
        }
        return true
    }

    private fun setListeners() {

        btnBack.setOnClickListener {
            finish()
        }

        btnSaveNote.setOnClickListener {
            noteViewModel.insert(getCurrentNote(), noteID)
        }

        btnDeleteNote.setOnClickListener {
            val note = getCurrentNote()
            note.id = noteID!!.toInt()
            noteViewModel.delete(note)
            disableEditTexts()
            Toast.makeText(this,"Note Deleted", Toast.LENGTH_SHORT).show()
            delayActivityFinish()
        }

        noteViewModel.emptyTitle.observe(this, Observer {
            if (it){
                Toast.makeText(this,"Type a Title, it can't be blank", Toast.LENGTH_SHORT).show()
            }
        })

        noteViewModel.noteSaved.observe(this, Observer {
            if (it){
                disableEditTexts()
                Toast.makeText(this,"Note Saved", Toast.LENGTH_SHORT).show()
                delayActivityFinish()
            }
        })
    }

    private fun disableEditTexts() {
        etNoteTitle.isEnabled = false
        etNoteText.isEnabled = false
    }

    private fun delayActivityFinish() {
        Handler().postDelayed({
            finish()
        }, 1500)
    }

    private fun getCurrentNote(): Note {
        val title = etNoteTitle.text.toString()
        val text = etNoteText.text.toString()
        val date = Calendar.getInstance().getTime().toString()
        val note = Note(title, text,date)
        return note
    }

}