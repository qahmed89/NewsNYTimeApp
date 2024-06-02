package com.ahmed.articleList.data.datasource.model

import com.ahmed.articleList.domain.model.MediaDomainModel
import com.google.gson.annotations.SerializedName

data class ArticleDomainModel(

    val abstract :String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("media")
    val media: List<MediaDomainModel>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    var url: String,
    val uri: String,
)