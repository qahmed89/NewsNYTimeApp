package com.ahmed.articleList.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MediaMetadataDomainModel(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)