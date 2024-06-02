package com.ahmed.articleList.data.datasource.remote

import com.ahmed.articleList.data.datasource.model.MostPopularDataModel

interface MostPopularDataSource {

   suspend fun getMostPopularArticles(period : String) : MostPopularDataModel?
}