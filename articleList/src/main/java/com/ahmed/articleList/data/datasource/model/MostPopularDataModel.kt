package com.ahmed.articleList.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MostPopularDataModel(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<ResultDataModel>,
    @SerializedName("status")
    val status: String
)