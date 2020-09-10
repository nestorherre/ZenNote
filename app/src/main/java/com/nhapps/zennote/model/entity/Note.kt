package com.nhapps.zennote.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(
    val title: String,
    val text: String,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}