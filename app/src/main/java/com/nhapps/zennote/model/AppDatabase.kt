package com.nhapps.zennote.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nhapps.zennote.model.dao.NoteDao
import com.nhapps.zennote.model.entity.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDB.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }


}