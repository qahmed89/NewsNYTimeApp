package com.ahmed.articleList.data.datasource.mapper

import com.ahmed.articleList.data.datasource.mock.MostPopularMocking
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMapperTest {


    @Test
    fun `when toMostPopularDomainModel is called`() {
        val popularDataModel: MostPopularDataModel = MostPopularMocking.fakeMostPopularDataModel
        val expected: MostPopularDomainModel =  MostPopularMocking.fakeMostPopularDomainModel
        val actual = popularDataModel.toMostPopularDomainModel()
        Assert.assertEquals(
            expected.results.first().url, actual.results.first().url
        )
    }
}