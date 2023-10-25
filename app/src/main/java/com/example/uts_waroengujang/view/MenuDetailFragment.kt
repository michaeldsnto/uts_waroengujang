package com.example.uts_waroengujang.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.DetailViewModel
import com.example.uts_waroengujang.viewmodel.MenuViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class MenuDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuId = arguments?.getString("menuId")
        val txtNamaDetail = view.findViewById<TextView>(R.id.txtNamaDetail)
        val txtDeskripsi = view.findViewById<TextView>(R.id.txtDeskripsi)
        val txtHargaDetail = view.findViewById<TextView>(R.id.txtHargaDetail)
        val photoDetail = view.findViewById<ImageView>(R.id.photoDetail)
        val txtJumlah = view.findViewById<TextView>(R.id.txtJumlah)
        var jumlah = 1

        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
        viewModel.fetch(menuId)
        viewModel.menuDetailLD.observe(viewLifecycleOwner, Observer { menu ->
            if (menu != null) {
                Log.d("Detail", menu.toString())
                txtNamaDetail.text = menu.nama
                txtDeskripsi.text = menu.deskripsi
                txtHargaDetail.text = "IDR " + menu.harga.toString()
                Picasso.get().load(menu.photoUrl).resize(200,200).centerCrop().into(photoDetail)
            }
        })

        val btnTambah = view.findViewById<Button>(R.id.btnTambah)
        val btnKurang = view.findViewById<Button>(R.id.btnKurang)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnTambah.setOnClickListener {
            jumlah++
            txtJumlah.text = jumlah.toString()
        }
        btnKurang.setOnClickListener {
            if (jumlah <= 0) {
                jumlah = 0
                txtJumlah.text = jumlah.toString()
            } else {
                jumlah--
                txtJumlah.text = jumlah.toString()
            }
        }
        btnAdd.setOnClickListener {

        }
    }
}