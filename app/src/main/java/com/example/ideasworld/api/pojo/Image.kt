package com.example.ideasworld.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("alt_description")
    @Expose
    val description: String,
    @SerializedName("urls")
    @Expose
    val imageUrls: ImageUrls,
    @SerializedName("likes")
    @Expose
    val likes: Int
)
