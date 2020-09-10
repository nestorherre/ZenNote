package com.nhapps.zennote.view.others

import com.nhapps.zennote.model.entity.Note

interface RVItemClickListener {
    fun onCellClickListener(note: Note)
}