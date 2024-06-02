package com.ahmed.boubyanapp.ui.persentaion

sealed interface MainEffect  {
    data object CloseJourney : MainEffect
    data object NavToPreviousScreen : MainEffect
}