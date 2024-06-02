package com.ahmed.articleList.presentation.screen.articleDetails

import com.ahmed.articleList.presentation.model.Article

sealed interface ArticleDetailsIntent {

    data class InitScreen (val article: Article) : ArticleDetailsIntent
    data class ClickOnGoToLick(val url :String) : ArticleDetailsIntent
}