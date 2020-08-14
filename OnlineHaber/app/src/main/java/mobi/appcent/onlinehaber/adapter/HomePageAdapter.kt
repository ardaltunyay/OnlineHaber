package mobi.appcent.onlinehaber.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.homepagelayout.view.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.ui.home.HomeFragmentDirections

import mobi.appcent.onlinehaber.util.downloadFromUrl
import mobi.appcent.onlinehaber.util.placeHolderProgressBar
import kotlin.collections.ArrayList


class HomePageAdapter(val newsList: ArrayList<ArticlesItem>) :

    RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {
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


        var people = newsList[position].url
        var title = newsList[position].title
        var date = newsList[position].publishedAt
        var news = newsList[position].content
        var image = newsList[position].urlToImage

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                0,
                "$title",
                "$people",
                "$date",
                "$news",
                "$image"
            )
            Navigation.findNavController(it).navigate(action)

        }
    }


    fun updateCountryList(newCountryList: List<ArticlesItem>) {


        newsList.clear()
        newsList.addAll(newCountryList)
        notifyDataSetChanged()

    }

}