package com.example.ideasworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ideasworld.pojo.PhotoInfo

@Database(entities = [PhotoInfo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    companion object {
        private val DB_NAME = "photo_database"
        private var db: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                db = instance
                // return instance
                instance
            }
        }
    }

    abstract fun photoDao(): PhotoDao
}