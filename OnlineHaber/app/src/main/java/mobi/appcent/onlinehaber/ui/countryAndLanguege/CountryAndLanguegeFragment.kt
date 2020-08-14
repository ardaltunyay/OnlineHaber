package mobi.appcent.onlinehaber.ui.countryAndLanguege

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_country_and_languege_list_dialog.*
import kotlinx.android.synthetic.main.homepagelayout.view.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.ui.home.HomeViewModel


class CountryAndLanguegeFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel: HomeViewModel

    private val newsAdapter = HomePageAdapter(arrayListOf())

    var countryAndLanguageValues: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_country_and_languege_list_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)


        radioChecked()

        getRememberData()
        butonClick()


    }


    public fun radioChecked() {

        RadioTurkey.setOnClickListener {
            countryAndLanguageValues = "tr"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }
        RadioGermany.setOnClickListener {
            countryAndLanguageValues = "de"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }
        RadioABD.setOnClickListener {
            countryAndLanguageValues = "us"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }
        RadioSpain.setOnClickListener {
            countryAndLanguageValues = "es"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }
        RadioEngland.setOnClickListener {
            countryAndLanguageValues = "ae"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }
        RadioFrance.setOnClickListener {
            countryAndLanguageValues = "fr"
            Log.e("bbbbbbbbbbbbbbbb", "$countryAndLanguageValues")
        }


    }

    fun butonClick() {

        select.setOnClickListener {

            viewModel.DetailApiCall("$countryAndLanguageValues")
            observeLiveData()

            rememberMe()
            dismiss()


        }

    }

    fun observeLiveData() {
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                newsAdapter.notifyDataSetChanged()

                newsAdapter.updateCountryList(news)

            }

        })

    }

    fun rememberMe() {
        val shared = requireActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

        Log.e("bbbbbbb", "$countryAndLanguageValues")
        shared.edit().putString("value", countryAndLanguageValues).commit()


    }

    fun getRememberData() {
        var shared = requireActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        var values = shared.getString("value", "")
        countryAndLanguageValues=values
        Log.e("ggggggggg", "$countryAndLanguageValues")
        if ("$values" == "tr") {
            RadioTurkey.isChecked = true

        }
        if ("$values" == "de") {
            RadioGermany.isChecked = true
        }
        if ("$values" == "us") {
            RadioABD.isChecked = true
        }
        if ("$values" == "be") {
            RadioSpain.isChecked = true
        }
        if ("$values" == "ae") {
            RadioEngland.isChecked = true
        }
        if ("$values" == "fr") {
            RadioFrance.isChecked = true
        }


    }


}