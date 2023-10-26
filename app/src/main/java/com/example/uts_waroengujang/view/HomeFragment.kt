package com.example.uts_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.HomeViewModel
import com.example.uts_waroengujang.viewmodel.WaitressViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var waitressModel : WaitressViewModel
    private lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waitressModel = ViewModelProvider(requireActivity()).get(WaitressViewModel::class.java)

        waitressModel.getWaitressName().observe(viewLifecycleOwner, Observer{ waitressName ->
            txtView1.text = waitressName
        })
        waitressModel.getWaitressPhoto().observe(viewLifecycleOwner, Observer{ waitressPhoto ->
            if (!waitressPhoto.isNullOrBlank()) {
                Picasso.get().load(waitressPhoto).resize(200,200).centerCrop().into(photoWaitress)
            }
        })

        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            imageView4.visibility = View.GONE
            textInputLayout3.visibility = View.GONE
            txtTableNumber.visibility = View.GONE
            txtNumber.visibility = View.VISIBLE
            btnChange.visibility = View.VISIBLE
            btnSubmit.visibility = View.GONE

            val txtTableNumber = view.findViewById<TextView>(R.id.txtTableNumber)
            txtNumber.text = "Table ${txtTableNumber.text}"
            txtInfo.text = "Currently Serving"

            val nomorMeja = txtTableNumber.text.toString()
            homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
            homeViewModel.setTableNumber(nomorMeja)
        }
        val btnChange = view.findViewById<Button>(R.id.btnChange)
        btnChange.setOnClickListener {
            imageView4.visibility = View.VISIBLE
            textInputLayout3.visibility = View.VISIBLE
            txtTableNumber.visibility = View.VISIBLE
            txtNumber.visibility = View.GONE
            btnChange.visibility = View.GONE
            btnSubmit.visibility = View.VISIBLE

            txtNumber.text = "Table"
        }
    }

}