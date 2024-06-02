package com.ahmed.articleList.presentation

import com.ahmed.articleList.data.datasource.model.ArticleDomainModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.presentation.model.Article

fun MostPopularDomainModel?.toArticleList() = this?.results?.map { it.toArticle() } ?: emptyList()

fun ArticleDomainModel?.toArticle() = Article(
    title = this?.title.orEmpty(),
    author = this?.title.orEmpty(),
    date = this?.publishedDate.orEmpty(),
    imageUrl = kotlin.runCatching {  this?.media?.last()?.mediaMetadata?.last()?.url.orEmpty()}.getOrDefault("https://images6.alphacoders.com/488/thumb-1920-488158.jpg"),
    abstract = this?.abstract.orEmpty(),
    url = this?.url.orEmpty(),
    uri = this?.uri.orEmpty()

)