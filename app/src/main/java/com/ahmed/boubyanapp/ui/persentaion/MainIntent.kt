package com.ahmed.boubyanapp.ui.persentaion

import com.ahmed.boubyanapp.R
import com.ahmed.core.composable.UiText

sealed interface MainIntent {
    data class ChangeAppBarForArticleListScreen(
        val appBarState: AppBarState = AppBarState(showNavigationIcon = false, showExitIcon = false , title = UiText.StringResource(
            R.string.article_list_screen
        ))
    ) : MainIntent

    data class ChangeAppBarForDetailsScreen(
        val appBarStat: AppBarState = AppBarState(showNavigationIcon = true, showExitIcon = false , title = UiText.StringResource(
            R.string.article_details_screen
        ))
    ) : MainIntent

    object ClickOnNavigationIcon : MainIntent
    object ClickOnCloseJourney : MainIntent
}