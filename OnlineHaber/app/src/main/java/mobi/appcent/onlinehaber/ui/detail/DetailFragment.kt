package mobi.appcent.onlinehaber.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_detail.*
import mobi.appcent.onlinehaber.R


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var newsUuid = 0

    private var people: String? = null
    private var date: String? = null
    private var title: String? = null
    private var news: String? = null
    private var image: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)


        val let = arguments?.let {
            newsUuid = DetailFragmentArgs.fromBundle(
                it
            ).newsUuD
            title = DetailFragmentArgs.fromBundle(
                it
            ).title
            date = DetailFragmentArgs.fromBundle(
                it
            ).date
            people = DetailFragmentArgs.fromBundle(
                it
            ).people
            news = DetailFragmentArgs.fromBundle(
                it
            ).news
            image = DetailFragmentArgs.fromBundle(
                it
            ).image
            /*  detailtitle.text=title
              detailpeoplename.text=people
              detailsDate.text=date
              detailnews.text=news

              detailsImageView.downloadFromUrl(image, placeHolderProgressBar(requireContext()))
  */
        }


        detailWebView.settings.javaScriptEnabled = true
        detailWebView.loadUrl("$people")

        observeLiveData()


    }


    fun observeLiveData() {

        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { news ->
            news?.let {


            }
        })

    }

}