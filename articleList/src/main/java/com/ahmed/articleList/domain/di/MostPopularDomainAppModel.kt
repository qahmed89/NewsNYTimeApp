package com.ahmed.articleList.domain.di

import com.ahmed.articleList.data.datasource.remote.MostPopularDataSource
import com.ahmed.articleList.data.datasource.remote.RemoteMostPopularDataSourceImpl
import com.ahmed.articleList.data.datasource.repo.MostPopularRepoImpl
import com.ahmed.articleList.domain.repo.MostPopularArticleRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
object MostPopularDomainAppModel {
    @Singleton

    @Provides
    fun provideMostPopularRepo(dataSource: MostPopularDataSource): MostPopularArticleRepo =
        MostPopularRepoImpl(dataSource)
}