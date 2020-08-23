package mobi.appcent.onlinehaber.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.favoritealertdialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.model.Favorite
import mobi.appcent.onlinehaber.ui.ınterface.RecyclerViewLongListenerInterface


class HomeFragment : Fragment(), RecyclerViewLongListenerInterface {


    val arrayList: MutableList<Favorite> = ArrayList()
    companion object {
        fun newInstance() = HomeFragment()
    }


    private lateinit var viewModel: HomeViewModel
    val newsAdapter = HomePageAdapter(arrayListOf(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(HomeViewModel::class.java)
        viewModel.homeApiCall()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter




        visible()
        recyclerLine()
        countryClick()
        languegeClick()
        searchClick()
        FavoriteClicked()
    }


    fun observeLiveData() {
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                recyclerView.visibility = View.VISIBLE
                newsAdapter.updateCountryList(news)
            }



        })

        viewModel.loadingProgres.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                if (it)
                {

                    recyclerView.visibility=View.GONE
                    progresbar.visibility=View.VISIBLE
                }
                else
                {
                    recyclerView.visibility=View.VISIBLE
                    progresbar.visibility=View.GONE

                }

            }


        })

    }

    fun visible() {
        languege.visibility = View.GONE
        county.visibility = View.GONE
        filter.setOnClickListener {

            if (languege.visibility == View.GONE && county.visibility == View.GONE) {
                languege.visibility = View.VISIBLE
                county.visibility = View.VISIBLE
                search.visibility = View.GONE

            } else {
                languege.visibility = View.GONE
                county.visibility = View.GONE
                search.visibility = View.VISIBLE
            }


        }
    }

    fun recyclerLine() {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    fun languegeClick() {
        languege.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToCountryAndLanguegeFragment()

            Navigation.findNavController(it).navigate(action)
        }

    }

    fun countryClick() {


        county.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToCountryAndLanguegeFragment()
            Navigation.findNavController(it).navigate(action)


        }

    }

    fun searchClick() {
        search.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }

    override fun onLongListeneer(position: Int, list: MutableList<ArticlesItem>) {
        val layoutInflater: LayoutInflater = LayoutInflater.from(activity)
        val view: View = layoutInflater.inflate(R.layout.favoritealertdialog, null)

        var publishedAt= list.get(position).publishedAt
        var author= list.get(position).author
        var content= list.get(position).content
        var description= list.get(position).description
        var title= list.get(position).title
        var url= list.get(position).url
        var urlToImage= list.get(position).urlToImage
        var uuid= list.get(position).uuid

        val alert: AlertDialog.Builder = AlertDialog.Builder(context)
        alert.setView(view)
        alert.setCancelable(true)
        val alertDialog: AlertDialog = alert.create()
       alertDialog.show()



               arrayList.clear()
               arrayList.add(Favorite("$publishedAt","$author","$urlToImage","$description","$title","$url","$content"))





        Log.e("arrayy","$arrayList")

        view.favoriInsert.setOnClickListener {
            viewModel.favoriteSQLite(arrayList)
            Toast.makeText(context,"Haber Favorilerinize Eklendi",Toast.LENGTH_SHORT).show()
            alertDialog.cancel()
        }


    }
    fun FavoriteClicked()
    {
        favoriButton.setOnClickListener {

val action=HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}

