package com.example.uts_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.HomeViewModel
import com.example.uts_waroengujang.viewmodel.MenuViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class MenuFragment : Fragment() {
    private lateinit var viewModel:MenuViewModel
    private lateinit var homeViewModel: HomeViewModel
    private val menuListAdapter = MenuAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = menuListAdapter

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        swipe.setOnRefreshListener {
            viewModel.refresh()
            swipe.isRefreshing = false
        }

        observeViewModel()
        val txtTable = view.findViewById<TextView>(R.id.txtTable)
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        homeViewModel.getTableNumber().observe(viewLifecycleOwner, Observer{ tableNumber ->
            if (!tableNumber.isNullOrBlank()) {
                txtTable.text = "Table Number $tableNumber"
            }

        })
        val btnChange = view.findViewById<Button>(R.id.btnChange)
        btnChange.setOnClickListener {
            val action = MenuFragmentDirections.actionChange()
            Navigation.findNavController(it).navigate(action)

        }

    }
    fun observeViewModel() {
        viewModel.menuLD.observe(viewLifecycleOwner, Observer {
            menuListAdapter.updateMenuList(it)
        })
        viewModel.menuLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}