package com.example.ideasworld.fragments.favoritesfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ideasworld.database.PhotoDatabase
import com.example.ideasworld.pojo.PhotoInfo

class FavoritesViewModel(application: Application): AndroidViewModel(application) {
    private val photoDB = PhotoDatabase.getInstance(application)
    val favoritePhotos: LiveData<List<PhotoInfo>> = photoDB.photoDao().getAllPhoto()
}