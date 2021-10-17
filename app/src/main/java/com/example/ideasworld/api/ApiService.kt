package com.example.ideasworld.api

import com.example.ideasworld.pojo.PhotoInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("photos/random")
    fun getPhotos(
        @Query("client_id") access_key: String,
        @Query("count") count: Int
    ): Single<List<PhotoInfo>>

    @GET("photos/{id}")
    fun getPhotoInfo(
        @Path("id") id: String,
        @Query("client_id") access_key: String
    ): Single<PhotoInfo>
}