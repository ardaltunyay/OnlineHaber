package mobi.appcent.onlinehaber.interfaces

import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.model.ArticlesItem

interface RecyclerViewLongListenerInterface {
    fun onLongListeneer(position:Int, list: MutableList<ArticlesItem>)
}