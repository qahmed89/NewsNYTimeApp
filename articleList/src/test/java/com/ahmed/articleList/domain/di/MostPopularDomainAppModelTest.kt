package com.ahmed.articleList.domain.di

import com.ahmed.articleList.data.datasource.di.DataSourceModule
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class MostPopularDomainAppModelTest {

    private lateinit var mostPopularDomainAppModel: MostPopularDomainAppModel


    @Before
    fun setUp() {
        mostPopularDomainAppModel = MostPopularDomainAppModel
    }


    @Test
    fun `when make instance from MostPopularRepo`(){
        val mostPopularRepo = mostPopularDomainAppModel.provideMostPopularRepo(mock())
        Assert.assertNotNull(mostPopularRepo)
    }

}