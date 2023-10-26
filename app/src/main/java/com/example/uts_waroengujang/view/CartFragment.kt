package com.example.uts_waroengujang.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subtotalTextView: TextView = view.findViewById(R.id.textViewSubtotal)
        val taxTextView: TextView = view.findViewById(R.id.textViewTax)
        val totalTextView: TextView = view.findViewById(R.id.textViewTotal)
        viewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCart)
        cartAdapter = CartAdapter(arrayListOf(), viewModel)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        viewModel.subtotalLD.observe(viewLifecycleOwner, Observer { subtotal ->
            subtotalTextView.text = "Rp. $subtotal"
        })

        viewModel.taxLD.observe(viewLifecycleOwner, Observer { tax ->
            taxTextView.text = "Rp. $tax"
        })

        viewModel.totalLD.observe(viewLifecycleOwner, Observer { total ->
            totalTextView.text = "Rp. $total"
        })

        viewModel.cartLD.observe(viewLifecycleOwner, Observer {
            Log.d("CartFragment", "Cart Updated: $it")
            if (it != null) {
                cartAdapter.updateCartList(it)
            }
        })

        val checkoutButton: Button = view.findViewById(R.id.checkoutButton)
        checkoutButton.setOnClickListener {

        }
    }
}