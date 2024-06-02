package com.ahmed.articleList.data.datasource.remote

import com.ahmed.articleList.data.datasource.api.PopularApi
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RemoteMostPopularDataSourceImplTest() {
    @Mock
    lateinit var api :PopularApi

    lateinit var remoteDataSource: RemoteMostPopularDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this);

        remoteDataSource = RemoteMostPopularDataSourceImpl(api)
    }



    @Test
    fun `get most popular articles`()  = runTest{
        val mockMostPopular  = mock<MostPopularDataModel>()
        whenever(api.getPopularMovies("1")).thenReturn(mockMostPopular)
        val articles = remoteDataSource.getMostPopularArticles("1")
        Assert.assertEquals(mockMostPopular,articles)
    }

}