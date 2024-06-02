package com.ahmed.articleList.data.datasource.di

import com.ahmed.articleList.data.datasource.api.PopularApi
import com.ahmed.articleList.data.datasource.remote.MostPopularDataSource
import com.ahmed.articleList.data.datasource.remote.RemoteMostPopularDataSourceImpl
import com.ahmed.articleList.data.datasource.repo.MostPopularRepoImpl
import com.ahmed.articleList.domain.repo.MostPopularArticleRepo
import com.ahmed.core.network.RetrofitClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Singleton
    @Provides
    fun provideMostPopularApi() :PopularApi = RetrofitClient.create(PopularApi::class.java)
    @Singleton

    @Provides
    fun provideMostPopularDataSource(api: PopularApi) :MostPopularDataSource= RemoteMostPopularDataSourceImpl(api)




}