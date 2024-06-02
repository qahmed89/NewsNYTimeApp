package com.ahmed.articleList.presentation.mapper

import com.ahmed.articleList.data.datasource.mapper.toMostPopularDomainModel
import com.ahmed.articleList.data.datasource.mock.MostPopularMocking
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.presentation.model.Article
import com.ahmed.articleList.presentation.toArticleList
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleMapperTest {
    @Test
    fun `when toMostPopularDomainModel is called`() {
        val popularDataModel: MostPopularDomainModel = MostPopularMocking.fakeMostPopularDomainModel
        val expected: List<Article> =  listOf( MostPopularMocking.fakeArticleData)
        val actual = popularDataModel.toArticleList()
        Assert.assertEquals(
            expected.first().url, actual.first().url
        )
    }
}