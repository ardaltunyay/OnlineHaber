package mobi.appcent.onlinehaber.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobi.appcent.onlinehaber.model.Response


class DetailViewModel : ViewModel() {

    val newsLiveData = MutableLiveData<List<Response>>()


}