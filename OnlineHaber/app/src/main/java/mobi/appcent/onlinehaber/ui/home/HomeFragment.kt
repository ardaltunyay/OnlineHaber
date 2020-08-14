package mobi.appcent.onlinehaber.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.ui.home.HomeFragmentDirections


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    val newsAdapter = HomePageAdapter(arrayListOf())

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
    }


    fun observeLiveData() {
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                recyclerView.visibility = View.VISIBLE
                newsAdapter.updateCountryList(news)
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
}

