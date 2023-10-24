package com.example.uts_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.MenuViewModel

class MenuFragment : Fragment() {
    private lateinit var viewModel:MenuViewModel
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