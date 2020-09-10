package com.nhapps.zennote.view.others

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhapps.zennote.R
import com.nhapps.zennote.model.entity.Note
import kotlinx.android.synthetic.main.item_recyclerview.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class RecyclerViewAdapter(var notes: List<Note>, val cellClickListener: RVItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note, cellClickListener: RVItemClickListener){
            itemView.tvNoteTitle.text = note.title
            itemView.tvNoteDate.text = getFormattedDate(note.date)
            itemView.setOnClickListener {
                cellClickListener.onCellClickListener(note)
            }
        }

        private fun getFormattedDate(date: String): String {
            return date.substring(4,10) + date.substring(date.length-5, date.length)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.bind(currentNote, cellClickListener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}