package com.ahmed.articleList.presentation.screen.articleDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ahmed.core.composable.UiText
import com.ahmed.mylibrary.R
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ArticleDetailScreen(
    statUi: ArticleDetailsStatUi,
    effect: SharedFlow<ArticleDetailsEffect>,
    onIntent: (ArticleDetailsIntent) -> Unit,
) {
    val uriHandler = LocalUriHandler.current


    LaunchedEffect(Unit) {
        effect.collect {
            when (it) {
                is ArticleDetailsEffect.OpenLinkInChrome -> {
                    uriHandler.openUri(it.url)
                }

            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = statUi.article?.title.orEmpty(),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "By ${statUi.article?.author}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = statUi.article?.date.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = rememberAsyncImagePainter(statUi.article?.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = statUi.article?.abstract.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(modifier = Modifier.fillMaxWidth(), onClick = { onIntent(ArticleDetailsIntent.ClickOnGoToLick(statUi.article?.url.orEmpty()))  }) {
            Text(text = UiText.StringResource(R.string.go_to_link).asString())
        }
    }
}
