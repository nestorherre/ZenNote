package com.nhapps.zennote.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhapps.zennote.R
import com.nhapps.zennote.model.entity.Note
import com.nhapps.zennote.utils.HideBar
import com.nhapps.zennote.view.others.RVItemClickListener
import com.nhapps.zennote.view.others.RecyclerViewAdapter
import com.nhapps.zennote.viewmodel.BrowseNotesViewModel
import com.nhapps.zennote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_browse_notes.*
import kotlinx.android.synthetic.main.activity_home.btnNewNote
import kotlinx.android.synthetic.main.activity_note.btnBack
import org.koin.android.viewmodel.ext.android.viewModel

class BrowseNotesActivity : AppCompatActivity(), RVItemClickListener {

    private lateinit var rvAdapter: RecyclerViewAdapter
    val browseNotesViewModel: BrowseNotesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_notes)
        HideBar.apply(this)
        setListeners()
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        rvAdapter = RecyclerViewAdapter(listOf(), this)
        rvAllNotes.layoutManager = LinearLayoutManager(this)
        rvAllNotes.adapter = rvAdapter
    }


    private fun setListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        btnNewNote.setOnClickListener{
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
        browseNotesViewModel.getAllNotes().observe(this, Observer {
            rvAdapter.notes = it
            rvAdapter.notifyDataSetChanged()
        })
    }

    // Called when an Item on the RecyclerView is clicked
    override fun onCellClickListener(note: Note) {
        val myIntent = Intent(this, NoteActivity::class.java)
        myIntent.putExtra("id", note.id.toString())
        myIntent.putExtra("title", note.title)
        myIntent.putExtra("text", note.text)
        startActivity(myIntent)
    }
}