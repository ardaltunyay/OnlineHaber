package mobi.appcent.onlinehaber.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            // level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val api = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getNewsApi(): NewsAPI {
        return api.create(NewsAPI::class.java)
    }
}