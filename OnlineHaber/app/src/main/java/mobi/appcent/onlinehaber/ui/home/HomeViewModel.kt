package mobi.appcent.onlinehaber.ui.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import mobi.appcent.onlinehaber.base.BaseViewModel
import mobi.appcent.onlinehaber.database.NewsDatabase

import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.model.Favorite
import mobi.appcent.onlinehaber.service.ApiKey
import mobi.appcent.onlinehaber.service.NewsAPIService
import mobi.appcent.onlinehaber.util.CustomSharedPreferences


class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private val newsApiService = NewsAPIService()

    var news = MutableLiveData<List<ArticlesItem>>()
    var loadingProgres = MutableLiveData<Boolean>()
    var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    override fun onCleared() {

        super.onCleared()

    }
    fun homeApiCall() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            homeGetDataApi()
        }
    }

    private fun getDataFromSQLite() {
        launch {
            val newss = NewsDatabase(getApplication()).newsDao().getAllNews()
            showNews(newss)
            Toast.makeText(getApplication(), "SQL den alındı11", Toast.LENGTH_SHORT).show()
        }
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
        loadingProgres.value = true
        disposable.add(
            newsApiService.getNewsApi()
                .getNews("apple", "2020-08-08,", "popularity", ApiKey.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        it.articles?.let { it1 -> storeInSQLite(it1) }
                        Toast.makeText(
                            getApplication(),
                            "internetten den alındı11",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    {
                        Log.d("API", it.message)
                    }
                )
        )
    }



    /*https://newsapi.org/v2/top-headlines?country=us&apiKey=632731ff030d44a3885c56f99b626125*/
    public fun detailGetApi(country: String) {
        loadingProgres.value = true
        disposable.add(
            newsApiService.getNewsApi()
                .getNewsCountryAndLanguege("$country", ApiKey.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        news.value = it.articles
                    },
                    {
                        Log.d("ERROR", it.message)
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
        loadingProgres.value = true
        disposable.add(
            newsApiService.getNewsApi()
                .getSearch(
                    "$currentType",
                    "",
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

    private fun showNews(newsList: List<ArticlesItem>) {
        news.value = newsList
        loadingProgres.value = false
    }

    private fun storeInSQLite(list: List<ArticlesItem>) {
        launch {
            val dao = NewsDatabase(getApplication()).newsDao()
            dao.deleteAllNews()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showNews(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    fun favoriteSQLite(list: MutableList<Favorite>) {
        launch {
            val daoo = NewsDatabase(getApplication()).favoriteDao()
            val listLong = daoo.insert(*list.toTypedArray())
            /*  var i = 0
              while (i < list.size) {
                  list[i].uuid = listLong[i].toInt()
                  i = i + 1

              }*/
        }
    }
}