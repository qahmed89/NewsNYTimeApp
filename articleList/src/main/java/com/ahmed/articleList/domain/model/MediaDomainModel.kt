package com.ahmed.articleList.domain.model

import com.ahmed.articleList.data.datasource.model.MediaMetadataDomainModel
import com.google.gson.annotations.SerializedName

data class MediaDomainModel(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadataDomainModel>,

    )