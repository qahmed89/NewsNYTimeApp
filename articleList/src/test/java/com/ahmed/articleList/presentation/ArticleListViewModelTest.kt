package com.ahmed.articleList.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.ahmed.articleList.data.datasource.mock.MostPopularMocking
import com.ahmed.articleList.domain.usecase.GetMostPopularArticle
import com.ahmed.articleList.presentation.screen.articleList.ArticleListEffect
import com.ahmed.articleList.presentation.screen.articleList.ArticleListIntent
import com.ahmed.articleList.presentation.screen.articleList.ArticleListStateUi
import com.ahmed.articleList.presentation.screen.articleList.ArticleListViewModel
import com.ahmed.presentaion.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ArticleListViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getMostPopularArticle: GetMostPopularArticle

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    lateinit var viewModel: ArticleListViewModel

    @Before
    fun setUp() {
        viewModel = ArticleListViewModel(getArticlesUseCase = getMostPopularArticle, savedStateHandle = savedStateHandle)
    }


    @Test
    fun `when Intent is InitScreen update state`() = runTest {
        val mockedData = MostPopularMocking.fakeMostPopularDomainModel
        val secondUpdateState =
            ArticleListStateUi(isLoading = false, articles = mockedData.toArticleList())
        whenever(getMostPopularArticle("1")).thenReturn(MostPopularMocking.fakeMostPopularDomainModel)
        viewModel.onIntent(ArticleListIntent.InitScreen("1"))

        viewModel.uiState.test {
            Assert.assertEquals(awaitItem(), secondUpdateState)
        }
    }

    @Test
    fun `when Intent is InitScreen update effect`() = runTest {
        val article = MostPopularMocking.fakeMostPopularDomainModel.toArticleList().first()
        viewModel.uiEffect .test {
            viewModel.onIntent(ArticleListIntent.ClickOnArticle(article))

            Assert.assertEquals(awaitItem(), ArticleListEffect.NavigateToDetailsArticle(article))
        }
    }

    @Test
    fun `when Intent is ClickOnAlertDialogCancelButton update effect`() = runTest {
        viewModel.uiEffect .test {
            viewModel.onIntent(ArticleListIntent.ClickOnAlertDialogCancelButton)

            Assert.assertEquals(awaitItem(), ArticleListEffect.FinishArticleListScreen)
        }
    }

    @Test
    fun `when Intent is ClickedOnPeriodButton update effect`() = runTest {
        viewModel.onIntent(ArticleListIntent.ClickedOnPeriodButton)
        viewModel.uiState .test {
            val stateUi = ArticleListStateUi(showExpendFloatButton = true)
            Assert.assertEquals(awaitItem(), stateUi)
        }
    }
    @Test
    fun `when Intent is ClickOnRetryAlertDialogButton update effect`() = runTest {
        whenever(savedStateHandle.get<ArticleListIntent>("lastIntent") ).thenReturn(ArticleListIntent.ClickOnChangePeriod("1"))
        whenever(getMostPopularArticle("1")).thenReturn(MostPopularMocking.fakeMostPopularDomainModel)
        viewModel.onIntent(ArticleListIntent.ClickOnRetryAlertDialogButton)

        val x = savedStateHandle.get<ArticleListIntent>("lastIntent")
        viewModel.uiState .test {
            val state2 = ArticleListStateUi(articles = MostPopularMocking.fakeMostPopularDomainModel.toArticleList())
            Assert.assertEquals(awaitItem(), state2)
        }
    }

    @Test
    fun `when Intent is ClickOnChangePeriod update effect`() = runTest {
        val mockedData = MostPopularMocking.fakeMostPopularDomainModel
        val secondUpdateState =
            ArticleListStateUi(isLoading = false, articles = mockedData.toArticleList())
        whenever(getMostPopularArticle("1")).thenReturn(MostPopularMocking.fakeMostPopularDomainModel)
        viewModel.onIntent(ArticleListIntent.ClickOnChangePeriod("1"))

        viewModel.uiState.test {
            Assert.assertEquals(awaitItem(), secondUpdateState)
        }
    }

    @Test
    fun `when Intent is ClickOnChangePeriod throw error`() = runTest {
        val mockedData = MostPopularMocking.fakeMostPopularDomainModel
        val secondUpdateState =
            ArticleListStateUi(showAlertDialog = true, isLoading = false)
        whenever(getMostPopularArticle("1")).thenThrow()
        viewModel.onIntent(ArticleListIntent.ClickOnChangePeriod("1"))

        viewModel.uiState.test {
            Assert.assertEquals(awaitItem(), secondUpdateState)
        }
    }
}