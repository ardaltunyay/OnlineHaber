package mobi.appcent.onlinehaber.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.service.ApiKey
import mobi.appcent.onlinehaber.service.NewsAPIService


class HomeViewModel : ViewModel() {

    private val newsApiService = NewsAPIService()
     val disposable = CompositeDisposable()
    var news = MutableLiveData<List<ArticlesItem>>()

    var loadingProgres=MutableLiveData<Boolean>()


    fun homeApiCall() {
        homeGetDataApi()
    }

    fun detailApiCall(country: String) {

        detailGetApi(country)
    }

    fun searchApiCall(
        currentType: String,
        countryText: String,
        hoodText: String,
        language: String,
        from: String,
        date: String,
        sortBy: String
    ) {

        searchGetApi(currentType, countryText, hoodText, language, from, date, sortBy)
    }

    private fun homeGetDataApi() {
        loadingProgres.value=true
        disposable.add(
            newsApiService.getNewsApi()
                .getNews("apple", "2020-08-08,", "popularity", ApiKey.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        news.value = it.articles
                        Log.d("API", it.toString())
                        loadingProgres.value=false
                    },
                    {
                        Log.d("API", it.message)
                    }
                )
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    /*https://newsapi.org/v2/top-headlines?country=us&apiKey=632731ff030d44a3885c56f99b626125*/
    public fun detailGetApi(country: String) {

        disposable.add(

            newsApiService.getNewsApi()
                .getNewsCountryAndLanguege("$country", ApiKey.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        news.value = it.articles
                        Log.d("search", it.toString())
                    },
                    {
                        Log.d("yenii", it.message)
                    }
                )
        )


    }

    public fun searchGetApi(
        currentType: String,
        countryText: String,
        hoodText: String,
        language: String,
        from: String,
        date: String,
        sortBy: String
    ) {
        disposable.add(

            newsApiService.getNewsApi()
                .getSearch(
                    "$currentType",
                    "$countryText",
                    "$hoodText",
                    "$language",
                    "$from",
                    "$date",
                    "$sortBy",
                    ApiKey.API_KEY
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        news.value = it.articles
                        Log.d("search", it.toString())
                    },
                    {
                        Log.d("yenii", it.message)
                    }
                )
        )

    }
}