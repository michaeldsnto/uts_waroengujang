package com.example.uts_waroengujang.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.WaitressViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    private lateinit var waitressModel : WaitressViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waitressModel = ViewModelProvider(requireActivity()).get(WaitressViewModel::class.java)

        waitressModel.getWaitressName().observe(viewLifecycleOwner, Observer{ waitressName ->
            txtNamaAkun.text = waitressName
        })
        waitressModel.getWaitressWork().observe(viewLifecycleOwner, Observer{ waitressWork ->
            txtWork.text = waitressWork
        })
        val id = view?.findViewById<TextView>(R.id.txtCurr)
        waitressModel.getWaitressPassword().observe(viewLifecycleOwner, Observer{ waitressPassword ->
            id?.setText(waitressPassword)
        })
        waitressModel.getWaitressWork().observe(viewLifecycleOwner, Observer{ waitressWork ->
            txtWork.text = waitressWork
        })
        waitressModel.getWaitressPhoto().observe(viewLifecycleOwner, Observer{ waitressPhoto ->
            if (!waitressPhoto.isNullOrBlank()) {
                Picasso.get().load(waitressPhoto).resize(200,200).centerCrop().into(photoAkun)
            }
        })
        btnSignout.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.putExtra("waitressName", "")
            intent.putExtra("waitressPassword", "")
            intent.putExtra("waitressWork", "")
            intent.putExtra("waitressPhoto", "")
            startActivity(intent)
        }
    }
}