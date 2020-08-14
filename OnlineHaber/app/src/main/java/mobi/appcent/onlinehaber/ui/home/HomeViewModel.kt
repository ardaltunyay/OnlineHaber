package mobi.appcent.onlinehaber.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.service.NewsAPIService


class HomeViewModel : ViewModel() {

    private val newsApiService = NewsAPIService()
    private val disposable = CompositeDisposable()
    var news = MutableLiveData<List<ArticlesItem>>()



    fun homeApiCall() {
        getDataApı()
    }

    fun DetailApiCall(country: String) {

        getDataApıı(country)
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

        getDataApııı(currentType, countryText, hoodText, language, from, date, sortBy)
    }

    private fun getDataApı() {

        disposable.add(
            newsApiService.getNewsApi()
                .getNews("apple", "2020-08-08,", "popularity", "632731ff030d44a3885c56f99b626125")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        news.value = it.articles
                        Log.d("API", it.toString())
                    },
                    {
                        Log.d("API", it.message)
                    }
                )
        )


    }

    /*https://newsapi.org/v2/top-headlines?country=us&apiKey=632731ff030d44a3885c56f99b626125*/
    public fun getDataApıı(country: String) {
        disposable.add(

            newsApiService.getNewsApi()
                .getNewsCountryAndLanguege("$country", "632731ff030d44a3885c56f99b626125")
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

    public fun getDataApııı(
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
                    "632731ff030d44a3885c56f99b626125"
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