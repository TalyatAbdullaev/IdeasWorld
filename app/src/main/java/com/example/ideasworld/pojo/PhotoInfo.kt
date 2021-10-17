package com.example.ideasworld.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class PhotoInfo(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    val id: String,
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("alt_description")
    @Expose
    val description: String?,
    @SerializedName("urls")
    @Expose
    @Embedded
    val photoUrls: PhotoUrls,
    @SerializedName("likes")
    @Expose
    val likes: Int
)
