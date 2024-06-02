package com.ahmed.boubyanapp.ui.persentaion

import androidx.lifecycle.viewModelScope
import com.ahmed.presentaion.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<MainStateUi, MainIntent, MainEffect>() {
    private val _mainStat = MutableStateFlow(MainStateUi())
    private val _mainEffect = MutableSharedFlow<MainEffect>()




     override fun onIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.ChangeAppBarForDetailsScreen -> changeStateForAppBarDetails(intent)
                is MainIntent.ChangeAppBarForArticleListScreen -> changeStateForAppBarHome(intent)
                MainIntent.ClickOnCloseJourney -> closeJourney()
                MainIntent.ClickOnNavigationIcon -> popToPreviousScreen()
            }
        }
    }


    private fun changeStateForAppBarHome(intent: MainIntent.ChangeAppBarForArticleListScreen) {
        _mainStat.update {
            it.copy(appBarState = intent.appBarState)
        }
    }

    private fun changeStateForAppBarDetails(intent: MainIntent.ChangeAppBarForDetailsScreen) {
        _mainStat.update {
            it.copy(appBarState = intent.appBarStat)
        }
    }

    private suspend fun popToPreviousScreen() {
        _mainEffect.emit(
            MainEffect.NavToPreviousScreen
        )
    }

    private suspend fun closeJourney() {
        _mainEffect.emit(
            MainEffect.CloseJourney
        )
    }

    override val uiState: StateFlow<MainStateUi>
        get() = _mainStat
    override val uiEffect: SharedFlow<MainEffect>
        get() = _mainEffect
}
