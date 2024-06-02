package com.ahmed.articleList.data.datasource.remote

import com.ahmed.articleList.data.datasource.api.PopularApi
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import javax.inject.Inject

class RemoteMostPopularDataSourceImpl @Inject constructor(
    private val apiService: PopularApi) :
    MostPopularDataSource {
    override suspend fun getMostPopularArticles(period: String): MostPopularDataModel? {

        return  apiService.getPopularMovies(period)
    }
}