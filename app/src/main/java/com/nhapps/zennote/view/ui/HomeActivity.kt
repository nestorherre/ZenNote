package com.nhapps.zennote.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nhapps.zennote.R
import com.nhapps.zennote.utils.HideBar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        HideBar.apply(this)
        setClickListeners()
    }

    private fun setClickListeners(){
        btnNewNote.setOnClickListener{
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
        btnBrowseNotes.setOnClickListener{
                val intent = Intent(this, BrowseNotesActivity::class.java )
                startActivity(intent)
            }
    }

}