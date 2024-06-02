package com.ahmed.articleList.domain.usecase

import com.ahmed.articleList.domain.repo.MostPopularArticleRepo
import javax.inject.Inject

class GetMostPopularArticle @Inject constructor(private val repository: MostPopularArticleRepo) {

    suspend operator fun invoke(period:String) = repository.getMostPopularArticles(period)
}