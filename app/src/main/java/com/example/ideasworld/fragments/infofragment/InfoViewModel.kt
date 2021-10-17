package com.example.ideasworld.fragments.infofragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ideasworld.api.ApiFactory
import com.example.ideasworld.database.PhotoDatabase
import com.example.ideasworld.pojo.PhotoInfo
import io.reactivex.rxjava3.schedulers.Schedulers

class InfoViewModel(application: Application): AndroidViewModel(application) {
    private val ACCESS_KEY = "V-5QjKuBbUDvqIlz81G6szrrmoUn8TiCB75eX9YOUxk"
    private val photoDB = PhotoDatabase.getInstance(application)
    val photoInfo: MutableLiveData<PhotoInfo> = MutableLiveData()

    fun loadPhotoInfo(id: String) {
        ApiFactory.apiService.getPhotoInfo(id, ACCESS_KEY)
            .subscribeOn(Schedulers.io())
            .subscribe({
                photoInfo.postValue(it)
                Log.d("TAG", it.toString())
            }, {
                Log.d("TAG", it.message.toString())
            })
    }

    fun loadPhotoInfoFromDB(id: String): PhotoInfo {
        return photoDB.photoDao().getPhoto(id)
    }

    fun photoExist(id: String): Boolean {
        return photoDB.photoDao().exists(id)
    }

    fun addToFavorite(photoInfo: PhotoInfo) {
        photoDB.photoDao().addPhoto(photoInfo)
    }

    fun deleteFromFavorite(photoInfo: PhotoInfo) {
        photoDB.photoDao().deletePhoto(photoInfo)
    }
}