package com.ahmed.articleList.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.ahmed.articleList.data.datasource.mock.MostPopularMocking
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsEffect
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsIntent
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsStatUi
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsViewModel
import com.ahmed.presentaion.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ArticleDetailsViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var viewModel: ArticleDetailsViewModel

    @Before
    fun setUp() {
        viewModel = ArticleDetailsViewModel()
    }

    @Test
    fun `when ArticleDetailsIntent is InitScreen update state`() = runTest {
        val article = MostPopularMocking.fakeMostPopularDomainModel.toArticleList().first()
        val state = ArticleDetailsStatUi(article = article)
        viewModel.onIntent(ArticleDetailsIntent.InitScreen(article))
        viewModel.uiState.test {
            Assert.assertEquals(awaitItem(), state)

        }
    }

    @Test
    fun `when ArticleDetailsIntent is ClickOnGoToLick emit  effect`() = runTest {
        val article = MostPopularMocking.fakeMostPopularDomainModel.toArticleList().first()
        viewModel.uiEffect.test {
            viewModel.onIntent(ArticleDetailsIntent.ClickOnGoToLick(article.url))

            Assert.assertEquals(awaitItem(), ArticleDetailsEffect.OpenLinkInChrome(article.url))

        }
    }
}