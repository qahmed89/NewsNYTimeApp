package com.ahmed.articleList.presentation.screen.articleList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ahmed.articleList.domain.usecase.GetMostPopularArticle
import com.ahmed.articleList.presentation.toArticleList
import com.ahmed.presentaion.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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
class ArticleListViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getArticlesUseCase: GetMostPopularArticle
) :
    BaseViewModel<ArticleListStateUi, ArticleListIntent, ArticleListEffect>() {
        companion object{
            private const val LAST_INTENT = "lastIntent"
        }
    private val _state = MutableStateFlow(ArticleListStateUi())
    private val _effect = MutableSharedFlow<ArticleListEffect>()
    private val handlerException = CoroutineExceptionHandler { _, exception ->
        _state.update {
            it.copy(showAlertDialog = true, isLoading = false)
        }
    }


    override fun onIntent(intent: ArticleListIntent) {
        viewModelScope.launch(handlerException) {
            when (intent) {
                is ArticleListIntent.InitScreen -> {
                    savedStateHandle[LAST_INTENT] = ArticleListIntent.InitScreen(intent.period)
                    getArticles(intent.period)
                }

                is ArticleListIntent.ClickOnArticle -> _effect.emit(
                    ArticleListEffect.NavigateToDetailsArticle(intent.article)
                )

                ArticleListIntent.ClickedOnPeriodButton -> _state.update {
                    it.copy(showExpendFloatButton = !it.showExpendFloatButton)
                }

                is ArticleListIntent.ClickOnChangePeriod -> {
                    savedStateHandle[LAST_INTENT] =
                        ArticleListIntent.ClickOnChangePeriod(intent.period)
                    getArticles(intent.period)
                }

                ArticleListIntent.ClickOnAlertDialogCancelButton -> _effect.emit(ArticleListEffect.FinishArticleListScreen)
                is ArticleListIntent.ClickOnRetryAlertDialogButton -> {
                    _state.update { it.copy(showAlertDialog = false) }
                    savedStateHandle.get<ArticleListIntent>(LAST_INTENT)?.let {
                        onIntent(it)
                        savedStateHandle.clearSavedStateProvider(LAST_INTENT)
                    }
                }
            }
        }
    }

    private suspend fun getArticles(period: String) {
        _state.update {
            it.copy(isLoading = true)
        }

        val result = getArticlesUseCase(period)
        _state.update {
            it.copy(isLoading = false, articles = result?.toArticleList().orEmpty())
        }

    }

    override val uiState: StateFlow<ArticleListStateUi>
        get() = _state.asStateFlow()
    override val uiEffect: SharedFlow<ArticleListEffect>
        get() = _effect.asSharedFlow()

}