package com.ahmed.articleList.data.datasource.repo

import com.ahmed.articleList.data.datasource.mapper.toMostPopularDomainModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.data.datasource.remote.MostPopularDataSource
import com.ahmed.articleList.domain.repo.MostPopularArticleRepo
import javax.inject.Inject

class MostPopularRepoImpl @Inject constructor(private val dataSource: MostPopularDataSource) :
    MostPopularArticleRepo {
    override suspend fun getMostPopularArticles(period: String): MostPopularDomainModel? {
        return dataSource.getMostPopularArticles(period)?.toMostPopularDomainModel()
    }
}