package com.ahmed.articleList.domain.repo

import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel

interface MostPopularArticleRepo {

    suspend fun getMostPopularArticles(period : String): MostPopularDomainModel?
}