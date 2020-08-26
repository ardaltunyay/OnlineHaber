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
import mobi.appcent.onlinehaber.interfaces.RecyclerViewLongListenerInterface
import mobi.appcent.onlinehaber.util.downloadFromUrl
import mobi.appcent.onlinehaber.util.placeHolderProgressBar


class HomePageAdapter(
    val newsList: ArrayList<ArticlesItem>,
    recyclerViewLongListeneer: RecyclerViewLongListenerInterface
) :
    RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {
    var rr = recyclerViewLongListeneer
    val listttt: MutableList<ArticlesItem> = ArrayList()

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
        onBindViewHolderFunction(holder, position)
    }

    fun updateCountryList(newCountryList: List<ArticlesItem>) {
        newsList.clear()
        newsList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    fun onBindViewHolderFunction(holder: HomePageViewHolder, position: Int) {
        holder.view.homePageTitleTextView.text = newsList[position].title
        holder.view.homePageContentTextView.text = newsList[position].description
        holder.view.homePageImageView.downloadFromUrl(
            newsList[position].urlToImage,
            placeHolderProgressBar(holder.view.context)
        )
        val people = newsList[position].url
        val title = newsList[position].title
        val date = newsList[position].publishedAt
        val news = newsList[position].content
        val image = newsList[position].urlToImage
        // listttt.add(position,ArticlesItem("$2017031","autohor","$image","desc","$title","sfsfd","content"))
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
        holder.view.setOnLongClickListener {
            rr.onLongListeneer(position, newsList)
            return@setOnLongClickListener true
        }
    }
}