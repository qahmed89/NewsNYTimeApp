package com.ahmed.articleList.data.datasource.repo

import com.ahmed.articleList.data.datasource.mapper.toMostPopularDomainModel
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.data.datasource.remote.MostPopularDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MostPopularRepoTest {
    @Mock
    lateinit var dataSources: MostPopularDataSource

    lateinit var repo : MostPopularRepoImpl

    @Before
    fun setUp() {
        repo = MostPopularRepoImpl(dataSources)
    }



    @Test
    fun `test get most popular`() = runTest{
        val mockData  : MostPopularDataModel = mock()
        whenever(dataSources.getMostPopularArticles("1")).thenReturn(mockData)
        val expected = mockData.toMostPopularDomainModel()
        val actual = repo.getMostPopularArticles("1")
        Assert.assertEquals(expected,actual)
    }
}