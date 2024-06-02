package com.ahmed.articleList.data.datasource.di

import com.ahmed.articleList.data.datasource.api.PopularApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataSourceModuleTest {

    private lateinit var dataSourceModule: DataSourceModule

    @Before
    fun setUp() {
        dataSourceModule = DataSourceModule
    }

    @Test
    fun `when make instance from popular Service`() {
        val api = dataSourceModule.provideMostPopularApi()
        Assert.assertNotNull(api)
    }

    @Test
    fun `when make instance from MostPopularDataSource`() {

        val datasource = dataSourceModule.provideMostPopularDataSource(dataSourceModule.provideMostPopularApi())
        Assert.assertNotNull(datasource)
    }

}