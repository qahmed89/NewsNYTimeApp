package com.ahmed.articleList.presentation.screen.articleList

import com.ahmed.articleList.presentation.model.Article

sealed interface ArticleListEffect {
    data class NavigateToDetailsArticle(val article: Article) : ArticleListEffect
    object FinishArticleListScreen : ArticleListEffect
}