package com.example.ideasworld.fragments.favoritesfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoritesViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}