package com.ahmed.articleList.presentation.screen.articleDetails

sealed interface ArticleDetailsEffect {
    data class OpenLinkInChrome(val url: String) : ArticleDetailsEffect
}