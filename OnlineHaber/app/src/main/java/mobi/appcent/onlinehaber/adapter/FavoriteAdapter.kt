package mobi.appcent.onlinehaber.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.homepagelayout.view.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.model.Favorite
import mobi.appcent.onlinehaber.ui.ınterface.FavoriteDeleteInterface
import mobi.appcent.onlinehaber.util.downloadFromUrl
import mobi.appcent.onlinehaber.util.placeHolderProgressBar


class FavoriteAdapter(val newsList: ArrayList<Favorite>,favoriteDeleteViewListeneer: FavoriteDeleteInterface) :


    RecyclerView.Adapter<FavoriteAdapter.HomePageViewHolder>() {


val ss=favoriteDeleteViewListeneer
    class HomePageViewHolder(var view: View) : RecyclerView.ViewHolder(view) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.homepagelayout, parent, false)
        return HomePageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {

        holder.view.homePageTitleTextView.text = newsList[position].title
        holder.view.homePageContentTextView.text = newsList[position].description
        holder.view.homePageImageView.downloadFromUrl(

            newsList[position].urlToImage,
            placeHolderProgressBar(holder.view.context)
        )
        var uuId=newsList[position].uuid
        holder.view.setOnLongClickListener {

            ss.onLongListeneer(uuId)

            return@setOnLongClickListener true
        }
    }


    fun updateCountryList(newCountryList: List<Favorite>) {


        newsList.clear()
        newsList.addAll(newCountryList)
        notifyDataSetChanged()


    }


}