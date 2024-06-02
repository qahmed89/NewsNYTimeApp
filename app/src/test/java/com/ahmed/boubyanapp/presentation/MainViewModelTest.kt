package com.ahmed.boubyanapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.ahmed.boubyanapp.ui.persentaion.MainEffect
import com.ahmed.boubyanapp.ui.persentaion.MainIntent
import com.ahmed.boubyanapp.ui.persentaion.MainStateUi
import com.ahmed.boubyanapp.ui.persentaion.MainViewModel
import com.ahmed.presentaion.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    lateinit var viewModel: MainViewModel
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        viewModel = MainViewModel()
        MainIntent.ChangeAppBarForArticleListScreen()
    }

    @Test
    fun `when send ClickOnCloseJourney emit on effect`() = runTest {
        val expected = MainEffect.CloseJourney
        viewModel.uiEffect.test {
            viewModel.onIntent(MainIntent.ClickOnCloseJourney)

            Assert.assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `when send ClickOnNavigationIcon emit on effect`() = runTest {
        val expected = MainEffect.NavToPreviousScreen
        viewModel.uiEffect.test {
            viewModel.onIntent(MainIntent.ClickOnNavigationIcon)

            Assert.assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `when send ChangeAppBarForDetailsScreen emit on effect`() = runTest {
        val expected = MainStateUi(appBarState = MainIntent.ChangeAppBarForDetailsScreen().appBarStat)
        viewModel.onIntent(MainIntent.ChangeAppBarForDetailsScreen())

        viewModel.uiState.test {

            expectThat(expected.appBarState.showNavigationIcon).isEqualTo( awaitItem().appBarState.showNavigationIcon)
        }
    }

    @Test
    fun `when send ChangeAppBarForArticleListScreen emit on effect`() = runTest {
        val expected = MainStateUi(appBarState = MainIntent.ChangeAppBarForArticleListScreen().appBarState)
        viewModel.onIntent(MainIntent.ChangeAppBarForArticleListScreen())

        viewModel.uiState.test {

            expectThat(expected.appBarState.showNavigationIcon).isEqualTo( awaitItem().appBarState.showNavigationIcon)
        }
    }

}