package com.ahmed.articleList.data.datasource.api

import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.mylibrary.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PopularApi {

    @GET("viewed/{period}.json?api-key=${BuildConfig.API_KEY}")
   suspend fun getPopularMovies(@Path("period" ) period: String ): MostPopularDataModel

}