package com.ahmed.boubyanapp.ui.persentaion

import com.ahmed.core.composable.UiText

data class MainStateUi (
    val appBarState : AppBarState = AppBarState()
)


data class AppBarState(
    val showNavigationIcon: Boolean = false,
    val showExitIcon: Boolean = false,
    val title : UiText = UiText.DynamicString("")
)