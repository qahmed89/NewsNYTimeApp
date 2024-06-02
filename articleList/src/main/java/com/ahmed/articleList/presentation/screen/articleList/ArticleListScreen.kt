package com.ahmed.articleList.presentation.screen.articleList

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahmed.articleList.presentation.model.Article
import com.ahmed.mylibrary.R
import com.ahmed.presentaion.asBundle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArticleListScreen(
    state: ArticleListStateUi,
    onIntent: (ArticleListIntent) -> Unit,
    effect: SharedFlow<ArticleListEffect>,
    navController: NavController

) {
    val scrollState = rememberLazyListState()
    val activity = LocalContext.current as Activity

    LaunchedEffect(true) {
        if (state.articles.isEmpty()) {
            onIntent(ArticleListIntent.InitScreen())
        }
        effect.collectLatest {
            when (it) {
                is ArticleListEffect.NavigateToDetailsArticle -> navController.navigate("articleDetails/${it.article.asBundle()}")
                ArticleListEffect.FinishArticleListScreen -> activity.finish()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        AlertErrorDialog(
            title = stringResource(id = state.alertDialogState.title),
            message = stringResource(id = state.alertDialogState.message),
            onDissmiss = { onIntent(ArticleListIntent.ClickOnAlertDialogCancelButton) },
            onRetry = { onIntent(ArticleListIntent.ClickOnRetryAlertDialogButton)  },
            showDialog = state.showAlertDialog)
        LazyColumn(
            state = scrollState,
            modifier = Modifier.padding(it)
        ) {
            items(state.articles) {
                ArticleItem(it) {
                    onIntent(ArticleListIntent.ClickOnArticle(it))
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.End) {
            if (state.showExpendFloatButton) {
                FloatingActionButton(
                    onClick = { onIntent(ArticleListIntent.ClickOnChangePeriod("1")) },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "1", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                }
                FloatingActionButton(
                    onClick = { onIntent(ArticleListIntent.ClickOnChangePeriod("7")) },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "7", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                }
                FloatingActionButton(
                    onClick = { onIntent(ArticleListIntent.ClickOnChangePeriod("30")) },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "30", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                }

            }
            FloatingActionButton(onClick = { onIntent(ArticleListIntent.ClickedOnPeriodButton) }) {
                Text(
                    "Period",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    AnimatedVisibility(
        visible = state.isLoading,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300)),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // semi-transparent background
        ) {
            CircularProgressIndicator()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(
    article: Article,
    onArticleClicked: () -> Unit
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        onClick = {
            onArticleClicked()
        }

    ) {
        AsyncImage(
            model = article.imageUrl,
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp),
            maxLines = 2,
        )
        Text(
            text = article.abstract,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(8.dp)
        )
    }

}


@Composable
fun AlertErrorDialog(
    title: String,
    message: String,
    onDissmiss: () -> Unit,
    onRetry: () -> Unit,
    showDialog: Boolean = false
) {
    if (!showDialog) return
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = message) },
        onDismissRequest = { onDissmiss() },
        dismissButton = {
            Button(onClick = {

                onDissmiss()
            }) {
                Text(text = stringResource(id = R.string.error_alert_dialog_Cancel))
            }
        }, confirmButton = {
            Button(onClick = { onRetry() }) {
                Text(text = stringResource(id = R.string.error_alert_dialog_retry))
            }
        }


    )
}