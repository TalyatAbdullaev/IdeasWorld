package com.example.ideasworld.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    private val ACCESS_KEY = "V-5QjKuBbUDvqIlz81G6szrrmoUn8TiCB75eX9YOUxk"
    private val BASE_URL = "https://api.unsplash.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



}