package com.ahmed.articleList.presentation.screen.articleList

import com.ahmed.articleList.presentation.model.Article
import com.ahmed.core.composable.UiText
import com.ahmed.mylibrary.R

data class ArticleListStateUi(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val showExpendFloatButton: Boolean = false,
    val showAlertDialog: Boolean = false,
    val alertDialogState: AlertDialogState = AlertDialogState()
)

data class AlertDialogState(
    val title : Int = R.string.error_title,
    val message : Int = R.string.error_message,
)