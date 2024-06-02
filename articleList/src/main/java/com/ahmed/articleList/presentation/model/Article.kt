package com.ahmed.articleList.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val author: String,
    val date: String,
    val imageUrl: String,
    val abstract: String,
    val uri: String,
    val url: String
) : Parcelable