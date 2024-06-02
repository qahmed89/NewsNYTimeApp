package com.ahmed.articleList.presentation.screen.articleList

import android.os.Parcelable
import com.ahmed.articleList.presentation.model.Article
import kotlinx.parcelize.Parcelize

sealed interface ArticleListIntent :Parcelable{
    @Parcelize
    data class InitScreen (val period: String = "1") : ArticleListIntent
    @Parcelize data class ClickOnArticle(val article: Article) : ArticleListIntent
    @Parcelize data class ClickOnChangePeriod(val period: String) : ArticleListIntent
    @Parcelize object ClickedOnPeriodButton : ArticleListIntent
    @Parcelize object ClickOnRetryAlertDialogButton : ArticleListIntent
    @Parcelize object ClickOnAlertDialogCancelButton : ArticleListIntent

}