package com.example.ideasworld.fragments.photosfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotosViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}