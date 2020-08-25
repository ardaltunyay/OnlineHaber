package mobi.appcent.onlinehaber.ui.favorite

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.favoritedeletealertdialog.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import mobi.appcent.onlinehaber.R
import mobi.appcent.onlinehaber.adapter.FavoriteAdapter
import mobi.appcent.onlinehaber.interfaces.FavoriteDeleteInterface


class FavoriteFragment : Fragment(), FavoriteDeleteInterface {
    private lateinit var viewModel: FavoriteViewModel
    var favoriteAdapter = FavoriteAdapter(arrayListOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteRecyclerView.adapter = favoriteAdapter
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        viewModel.getDataFromSQLite()
        observeLiveData()
        recyclerLine()

    }

    fun recyclerLine() {
        favoriteRecyclerView.addItemDecoration(
            DividerItemDecoration(
                favoriteRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observeLiveData() {
        viewModel.favoriteList.observe(viewLifecycleOwner, Observer { favorite ->
            favorite?.let {
                favoriteRecyclerView.visibility = View.VISIBLE
                favoriteAdapter.updateCountryList(favorite)
            }
        })
    }

    override fun onLongListeneer(position: Int) {
        val layoutInflater: LayoutInflater = LayoutInflater.from(activity)
        val view: View = layoutInflater.inflate(R.layout.favoritedeletealertdialog, null)
        val alert: AlertDialog.Builder = AlertDialog.Builder(context)
        alert.setView(view)
        alert.setCancelable(true)
        val alertDialog: AlertDialog = alert.create()
        alertDialog.show()
        view.favoriDelete.setOnClickListener {
            viewModel.deleteFromSQLite(position)
            Toast.makeText(context, "Kaydelilenlerden Kaldırıldı", Toast.LENGTH_SHORT).show()
            alertDialog.cancel()
            viewModel.getDataFromSQLite()
        }
    }
}