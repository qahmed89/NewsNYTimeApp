package com.ahmed.articleList.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MostPopularDomainModel(
    val results: List<ArticleDomainModel>,
)