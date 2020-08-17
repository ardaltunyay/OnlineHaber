package mobi.appcent.onlinehaber.ui.search

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_search.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.ui.home.HomeViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {

    /*
    * TODO Dil ve Ülke alanları için recyclerview kullansan daha iyi olabilir
    * */

    private lateinit var viewModel: HomeViewModel
    private val newsAdapter = HomePageAdapter(arrayListOf())

    private lateinit var fragmentmanager: FragmentManager
    private lateinit var fragmenttransiction: FragmentTransaction

    var popularityAndSimilarity = ArrayList<String>()
    var releaseDateArray = ArrayList<String>()
    private lateinit var dataAdapter: ArrayAdapter<String>
    private lateinit var dataAdapterTwo: ArrayAdapter<String>

    var contentText = ""
    var hoodText = ""
    var dateTextOne = ""
    var dateTextTwo = ""
    var languageChek = ""
    var countryCheck = ""
    var popularity = ""

    var isRemember = false;

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val mounth = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)


        // countryChecked()
        dateButtonClick()
        relaseDateSpinnerClick()
        popularityAndSimilaritySpinnerClick()
        DetailSearchButtonClick()
        visible()
        languageClick()
        countryClick()

    }

    fun visible() {
        languegeTurkeySelect.visibility = View.GONE
        languegeGermanySelect.visibility = View.GONE
        languegeEnglandSelect.visibility = View.GONE
        languegeABDSelect.visibility = View.GONE
        languegeSpainSelect.visibility = View.GONE
        languegeFranceSelect.visibility = View.GONE

        countryEnglandSelect.visibility = View.GONE
        countryFrenceSelect.visibility = View.GONE
        countryABDSelect.visibility = View.GONE
        countrySpainSelect.visibility = View.GONE
        countryGermanySelect.visibility = View.GONE
        countryTurkeySelect.visibility = View.GONE

    }

    public fun languageClick() {
        turkish.setOnClickListener {
            languageChek = "tr"
            languegeTurkeySelect.visibility = View.VISIBLE
            languegeGermanySelect.visibility = View.GONE
            languegeEnglandSelect.visibility = View.GONE
            languegeABDSelect.visibility = View.GONE
            languegeSpainSelect.visibility = View.GONE
            languegeFranceSelect.visibility = View.GONE
            searchTextView.text=("TR/TR")
        }
        germany.setOnClickListener {
            languageChek = "de"
            languegeGermanySelect.visibility = View.VISIBLE
            languegeTurkeySelect.visibility = View.GONE
            languegeEnglandSelect.visibility = View.GONE
            languegeABDSelect.visibility = View.GONE
            languegeSpainSelect.visibility = View.GONE
            languegeFranceSelect.visibility = View.GONE
            searchTextView.text=("DE/DE")
        }
        ABD.setOnClickListener {
            languageChek = "us"
            languegeABDSelect.visibility = View.VISIBLE
            languegeGermanySelect.visibility = View.GONE
            languegeTurkeySelect.visibility = View.GONE
            languegeEnglandSelect.visibility = View.GONE
            languegeSpainSelect.visibility = View.GONE
            languegeFranceSelect.visibility = View.GONE
            searchTextView.text=("US/US")
        }
        spanish.setOnClickListener {
            languageChek = "es"
            languegeSpainSelect.visibility = View.VISIBLE
            languegeABDSelect.visibility = View.GONE
            languegeGermanySelect.visibility = View.GONE
            languegeTurkeySelect.visibility = View.GONE
            languegeEnglandSelect.visibility = View.GONE
            languegeFranceSelect.visibility = View.GONE
            searchTextView.text=("ES/ES")
        }
        england.setOnClickListener {
            languageChek = "uk"
            languegeEnglandSelect.visibility = View.VISIBLE
            languegeSpainSelect.visibility = View.GONE
            languegeABDSelect.visibility = View.GONE
            languegeGermanySelect.visibility = View.GONE
            languegeTurkeySelect.visibility = View.GONE
            languegeFranceSelect.visibility = View.GONE
            searchTextView.text=("EN/EN")
        }
        frence.setOnClickListener {
            languageChek = "fr"
            languegeFranceSelect.visibility = View.VISIBLE
            languegeEnglandSelect.visibility = View.GONE
            languegeSpainSelect.visibility = View.GONE
            languegeABDSelect.visibility = View.GONE
            languegeGermanySelect.visibility = View.GONE
            languegeTurkeySelect.visibility = View.GONE
            searchTextView.text=("FR/FR")
        }

    }

    public fun countryClick() {
        turkishCountry.setOnClickListener {
            countryCheck = "tr"
            countryTurkeySelect.visibility = View.VISIBLE
            countryFrenceSelect.visibility = View.GONE
            countryABDSelect.visibility = View.GONE
            countryEnglandSelect.visibility = View.GONE
            countryGermanySelect.visibility = View.GONE
            countrySpainSelect.visibility = View.GONE

        }
        germanyCountry.setOnClickListener {
            countryCheck = "de"
            countryGermanySelect.visibility = View.VISIBLE
            countryTurkeySelect.visibility = View.GONE
            countryFrenceSelect.visibility = View.GONE
            countryABDSelect.visibility = View.GONE
            countryEnglandSelect.visibility = View.GONE
            countrySpainSelect.visibility = View.GONE
        }
        ABDCountry.setOnClickListener {
            countryCheck = "us"
            countryGermanySelect.visibility = View.GONE
            countryTurkeySelect.visibility = View.GONE
            countryFrenceSelect.visibility = View.GONE
            countryABDSelect.visibility = View.VISIBLE
            countryEnglandSelect.visibility = View.GONE
            countrySpainSelect.visibility = View.GONE
        }
        spainCountry.setOnClickListener {
            countryCheck = "es"
            countryGermanySelect.visibility = View.GONE
            countryTurkeySelect.visibility = View.GONE
            countryFrenceSelect.visibility = View.GONE
            countryABDSelect.visibility = View.GONE
            countryEnglandSelect.visibility = View.GONE
            countrySpainSelect.visibility = View.VISIBLE
        }
        englandCountry.setOnClickListener {
            countryCheck = "uk"
            countryGermanySelect.visibility = View.GONE
            countryTurkeySelect.visibility = View.GONE
            countryFrenceSelect.visibility = View.GONE
            countryABDSelect.visibility = View.GONE
            countryEnglandSelect.visibility = View.VISIBLE
            countrySpainSelect.visibility = View.GONE
        }
        frenceCountry.setOnClickListener {
            countryCheck = "fr"
            countryGermanySelect.visibility = View.GONE
            countryTurkeySelect.visibility = View.GONE
            countryFrenceSelect.visibility = View.VISIBLE
            countryABDSelect.visibility = View.GONE
            countryEnglandSelect.visibility = View.GONE
            countrySpainSelect.visibility = View.GONE
        }
    }


    public fun dateButtonClick() {
        dateOne.setOnClickListener {

            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    var monthh = month + 1
                    dateTextOne = ("$year/$monthh/$dayOfMonth")
                    dateOne.setText(dateTextOne)
                },
                year,
                mounth,
                day
            )
            dpd.show()

        }
        dateTwo.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    var monthh = month + 1
                    dateTextTwo = ("$year-$monthh-$dayOfMonth")
                    dateTwo.setText(dateTextTwo)
                },
                year,
                mounth,
                day
            )
            dpd.show()

        }
    }

    fun DetailSearchButtonClick() {
        resultButton.setOnClickListener {
            contentText = contentSearchEdittext.text.toString()
            hoodText = hoodSearchEdittext.text.toString()
            viewModel.searchApiCall(
                "$contentText",
                "$countryCheck",
                "$hoodText",
                "$languageChek",
                "$dateTextOne",
                "$dateTextTwo",
                "$popularity"
            )
            observeLiveData()


            findNavController().popBackStack()

        }

    }


    fun relaseDateSpinnerClick() {

        val presentTense = Date()
        val df: DateFormat = SimpleDateFormat("yyyy/MM/dd")
        Log.d("yyyyyyy", "${df.format(presentTense)}")

        releaseDateArray.add("Yayın Tarihi")
        releaseDateArray.add("Yeni Haberler")
        releaseDateArray.add("Eski Haberler")
        releaseDateArray.add("Manşetler")

        dataAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            releaseDateArray
        )
        releaseDate.adapter = dataAdapter
        releaseDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    dateTextOne = "${df.format(presentTense)}"
                    Log.d("yyyyyyy", "${df.format(presentTense)}")
                }
                if (position == 2) {
                    popularity = "publishedAt"
                    Log.d("yyyyyyy", "$popularity")
                }
                if (position == 3) {
                    popularity = "relevancy"
                    Log.d("yyyyyyy", "$popularity")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


    }

    fun popularityAndSimilaritySpinnerClick() {
        popularityAndSimilarity.add("Popülerite-Benzerlik")
        popularityAndSimilarity.add("En Çok Okunan")
        popularityAndSimilarity.add("Son Dakika")
        popularityAndSimilarity.add("Manşetler")

        dataAdapterTwo = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            popularityAndSimilarity
        )
        popularityAndSimilarityy.adapter = dataAdapterTwo
        popularityAndSimilarityy.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 1) {
                        popularity = "popularity"
                        Log.d("yyyyyyy", "$popularity")
                    }
                    if (position == 2) {
                        popularity = "publishedAt"
                        Log.d("yyyyyyy", "$popularity")
                    }
                    if (position == 3) {
                        popularity = "relevancy"
                        Log.d("yyyyyyy", "$popularity")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }

    fun observeLiveData() {
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {


                newsAdapter.updateCountryList(news)

            }

        })

    }

}

