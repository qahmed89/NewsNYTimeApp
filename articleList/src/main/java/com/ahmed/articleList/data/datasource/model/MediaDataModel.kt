package com.ahmed.articleList.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MediaDataModel(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadataDataModel>,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String
)