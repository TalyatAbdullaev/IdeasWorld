package com.example.ideasworld.api

import com.example.ideasworld.api.pojo.Image
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    fun getPhotos(): Call<List<Image>>
}