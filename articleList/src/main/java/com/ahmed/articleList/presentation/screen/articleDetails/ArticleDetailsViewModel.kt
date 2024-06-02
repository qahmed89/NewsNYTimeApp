package com.ahmed.articleList.presentation.screen.articleDetails

import androidx.lifecycle.viewModelScope
import com.ahmed.articleList.presentation.model.Article
import com.ahmed.presentaion.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(

) :BaseViewModel<ArticleDetailsStatUi,ArticleDetailsIntent,ArticleDetailsEffect>(){
    private val _state = MutableStateFlow(ArticleDetailsStatUi())
    private val _effect = MutableSharedFlow<ArticleDetailsEffect>()
    override val uiState: StateFlow<ArticleDetailsStatUi>
        get() = _state.asStateFlow()
    override val uiEffect: SharedFlow<ArticleDetailsEffect>
        get() = _effect.asSharedFlow()

    override fun onIntent(intent: ArticleDetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is ArticleDetailsIntent.InitScreen -> _state.update { it.copy(article = intent.article) }
                is ArticleDetailsIntent.ClickOnGoToLick -> _effect.emit(
                    ArticleDetailsEffect.OpenLinkInChrome(
                        intent.url
                    )
                )
            }
        }
    }

}