package mobi.appcent.onlinehaber.service

import io.reactivex.Single
import mobi.appcent.onlinehaber.model.GetNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    fun getNews(
        @Query("q") currentType: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Single<GetNewsResponse>

    /*https://newsapi.org/v2/top-headlines?country=us&apiKey=632731ff030d44a3885c56f99b626125*/
    @GET("top-headlines")
    fun getNewsCountryAndLanguege(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<GetNewsResponse>

    @GET("everything")
    fun getSearch(
        @Query("q") currentType: String,
        @Query("country") country: String,
        @Query("qInTitle") qInTitle: String,
        @Query("language") language: String,
        @Query("from") from: String,
        @Query("to") date: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Single<GetNewsResponse>
}