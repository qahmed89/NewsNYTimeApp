package com.ahmed.boubyanapp.ui.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahmed.boubyanapp.ui.persentaion.MainIntent
import com.ahmed.boubyanapp.ui.persentaion.MainStateUi


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithNavigationAndExitIcon(
    text: String = "",
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    state: MainStateUi,
    onIntent: (MainIntent) -> Unit
) {
    val navigationIcon: @Composable (() -> Unit) = {
        IconButton(onClick = { onIntent(MainIntent.ClickOnNavigationIcon) }) {
            Icon(startIcon ?: Icons.Filled.ArrowBack, null)
        }
    }

    val action :@Composable RowScope.() -> Unit= {
        if (state.appBarState.showExitIcon) {
            IconButton(onClick = { onIntent(MainIntent.ClickOnCloseJourney) }) {
                Icon(endIcon ?: Icons.Filled.Close, null)
            }
        }
    }


        TopAppBar(
            title = { Text(state.appBarState.title.asString()) },
            navigationIcon = {if (state.appBarState.showNavigationIcon) {
                navigationIcon()
            } else null},
            actions =  action,
            colors = TopAppBarDefaults.topAppBarColors(containerColor = FloatingActionButtonDefaults.containerColor)

        )
    }