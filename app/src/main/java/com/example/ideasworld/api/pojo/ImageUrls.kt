package com.example.ideasworld.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageUrls(
    @SerializedName("raw")
    @Expose
    val raw: String,
    @SerializedName("full")
    @Expose
    val full: String,
    @SerializedName("regular")
    @Expose
    val regular: String,
    @SerializedName("small")
    @Expose
    val small: String,
    @SerializedName("thumb")
    @Expose
    val thumb: String
)
