package mobi.appcent.onlinehaber.ui.countryAndLanguege

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_country_and_languege_list_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.HomePageAdapter
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.ui.home.HomeViewModel
import mobi.appcent.onlinehaber.interfaces.RecyclerViewLongListenerInterface


class CountryAndLanguegeFragment : BottomSheetDialogFragment(), RecyclerViewLongListenerInterface {

    override fun onLongListeneer(position: Int, list: MutableList<ArticlesItem>) {
        TODO("Not yet implemented")
    }

    val sSharedPreferences = mobi.appcent.onlinehaber.util.SharedPreferences()
    private lateinit var viewModel: HomeViewModel
    private val newsAdapter = HomePageAdapter(arrayListOf(), this)
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
        }
        RadioGermany.setOnClickListener {
            countryAndLanguageValues = "de"
        }
        RadioABD.setOnClickListener {
            countryAndLanguageValues = "us"
        }
        RadioSpain.setOnClickListener {
            countryAndLanguageValues = "es"
        }
        RadioEngland.setOnClickListener {
            countryAndLanguageValues = "ae"
        }
        RadioFrance.setOnClickListener {
            countryAndLanguageValues = "fr"
        }
    }

    fun butonClick() {
        select.setOnClickListener {
            viewModel.detailApiCall("$countryAndLanguageValues")
            //observeLiveData()
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
        viewModel.loadingProgres.observe(requireActivity(), Observer { loading ->
            loading?.let {
                if (it) {
                    progresbar.visibility = View.VISIBLE
                } else {
                    progresbar.visibility = View.GONE
                }
            }
        })
    }

    fun rememberMe() {
        // sade bu sınıfta kullanacaksam shared preferencesi böyle kullanmalıyım
        // val shared = requireActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        //  shared.edit().putString("value", countryAndLanguageValues).commit()
        sSharedPreferences.save(requireContext(), countryAndLanguageValues)
    }

    fun getRememberData() {
        // sade bu sınıfta kullanacaksam shared preferencesi böyle kullanmalıyım
        //   var shared = requireActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        //  var values = shared.getString("value", "")
        var values = sSharedPreferences.getValue(requireContext())

        countryAndLanguageValues = values
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
        if ("$values" == "es") {
            RadioSpain.isChecked = true
        }
    }
}