package com.ahmed.articleList.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MediaMetadataDataModel(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)