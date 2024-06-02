package com.ahmed.boubyanapp.ui.persentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed.articleList.presentation.model.Article
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailScreen
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsIntent
import com.ahmed.articleList.presentation.screen.articleDetails.ArticleDetailsViewModel
import com.ahmed.articleList.presentation.screen.articleList.ArticleListScreen
import com.ahmed.articleList.presentation.screen.articleList.ArticleListViewModel
import com.ahmed.boubyanapp.ui.composable.AppBarWithNavigationAndExitIcon
import com.ahmed.boubyanapp.ui.theme.BoubyanAppTheme
import com.ahmed.presentaion.toParcelable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoubyanAppTheme {
                val navController = rememberNavController()
                val mainViewMode: MainViewModel = hiltViewModel()
                val mainState by mainViewMode.uiState.collectAsState()
                LaunchedEffect(key1 = Unit) {
                    mainViewMode.uiEffect.collectLatest {
                        when (it) {
                            MainEffect.CloseJourney -> finish()
                            MainEffect.NavToPreviousScreen -> navController.popBackStack()
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBarWithNavigationAndExitIcon(
                            state = mainState,
                            onIntent = {
                                mainViewMode.onIntent(it)
                            })
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "articleList",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("articleList") {
                            val viewModel: ArticleListViewModel = hiltViewModel()
                            val state by viewModel.uiState.collectAsState()
                            LaunchedEffect(key1 = Unit) {
                                mainViewMode.onIntent(MainIntent.ChangeAppBarForArticleListScreen())
                            }
                            ArticleListScreen(
                                state = state,
                                onIntent = viewModel::onIntent,
                                effect = viewModel.uiEffect,
                                navController
                            )
                        }
                        composable("articleDetails/{data}") {
                            val args: Article =
                                it.arguments?.toParcelable<Article>("data") ?: Article(
                                    "",
                                    "",
                                    "",
                                    "",
                                    "","",""
                                )
                            val articleDetailsViewModel: ArticleDetailsViewModel = hiltViewModel()
                            val state by articleDetailsViewModel.uiState.collectAsState()
                            LaunchedEffect(key1 = args) {
                                mainViewMode.onIntent(MainIntent.ChangeAppBarForDetailsScreen())
                                articleDetailsViewModel.onIntent(ArticleDetailsIntent.InitScreen(args))
                            }
                            ArticleDetailScreen(
                                statUi = state,
                                onIntent = articleDetailsViewModel::onIntent,
                                effect = articleDetailsViewModel.uiEffect)
                        }
                    }

                }
            }
        }
    }
}

