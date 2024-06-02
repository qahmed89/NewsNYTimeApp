package com.ahmed.articleList.domain.usecase

import com.ahmed.articleList.data.datasource.mock.MostPopularMocking
import com.ahmed.articleList.domain.repo.MostPopularArticleRepo
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMostPopularArticleTest {
    @Mock
    lateinit var repo: MostPopularArticleRepo

    lateinit var useCase: GetMostPopularArticle

    @Before
    fun setUp() {
         useCase = GetMostPopularArticle(repo)
    }


    @Test
    fun `when call GetMostPopularArticle get MostPopularArticleDomainModel`() = runTest {
        val expected = MostPopularMocking.fakeMostPopularDomainModel
        whenever(repo.getMostPopularArticles("1")).thenReturn(expected)
        val result = useCase("1")
        Assert.assertEquals(expected, result)
    }
}