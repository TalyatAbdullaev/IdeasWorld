package com.example.ideasworld.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ideasworld.pojo.PhotoInfo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos")
    fun getAllPhoto(): LiveData<List<PhotoInfo>>

    @Delete
    fun deletePhoto(photo: PhotoInfo)

    @Insert
    fun addPhoto(photo: PhotoInfo)

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: String): PhotoInfo

    @Query("SELECT EXISTS (SELECT 1 FROM photos WHERE id = :id)")
    fun exists(id: String): Boolean

}