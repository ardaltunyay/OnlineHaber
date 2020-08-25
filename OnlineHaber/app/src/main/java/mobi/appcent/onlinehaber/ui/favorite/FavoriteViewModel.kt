package mobi.appcent.onlinehaber.ui.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import mobi.appcent.onlinehaber.base.BaseViewModel
import mobi.appcent.onlinehaber.database.NewsDatabase
import mobi.appcent.onlinehaber.model.Favorite

class FavoriteViewModel(application: Application) : BaseViewModel(application) {
    var favoriteList = MutableLiveData<List<Favorite>>()
    public fun getDataFromSQLite() {
        launch {
            val newss = NewsDatabase(getApplication()).favoriteDao().select()
            show(newss)
        }
    }

    public fun deleteFromSQLite(position: Int) {
        launch {
            val delete = NewsDatabase(getApplication()).favoriteDao()
            delete.delete(position)
        }
    }

    public fun show(news: List<Favorite>) {
        favoriteList.value = news
    }
}