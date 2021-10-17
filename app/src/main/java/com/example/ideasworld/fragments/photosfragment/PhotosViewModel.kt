package com.example.ideasworld.fragments.photosfragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ideasworld.api.ApiFactory
import com.example.ideasworld.pojo.PhotoInfo
import io.reactivex.rxjava3.schedulers.Schedulers

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    private val ACCESS_KEY = "V-5QjKuBbUDvqIlz81G6szrrmoUn8TiCB75eX9YOUxk"
    val photos: MutableLiveData<List<PhotoInfo>> = MutableLiveData()

    fun loadImages() {
        ApiFactory.apiService.getPhotos(ACCESS_KEY, 30)
            .subscribeOn(Schedulers.io())
            .subscribe({
                photos.postValue(it)
                Log.d("TAG", it.toString())
            }, {
                Log.d("TAG", it.message.toString())
            })
    }
}