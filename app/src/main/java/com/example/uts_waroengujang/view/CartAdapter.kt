package com.example.uts_waroengujang.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.model.Cart
import com.example.uts_waroengujang.viewmodel.CartViewModel
import com.squareup.picasso.Picasso

class CartAdapter(private var cartList: List<Cart>?, private val cartViewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNamaMenu: TextView = view.findViewById(R.id.txtNamaMenu)
        val editTextQuantity: EditText = view.findViewById(R.id.editTextQuantity)
        val imgMakan: ImageView = view.findViewById(R.id.imageViewMakan)
        val btnTambah: Button = view.findViewById(R.id.btnTambah)
        val btnKurang: Button = view.findViewById(R.id.btnKurang)
        val txtHarga: TextView = view.findViewById(R.id.txtHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartList?.get(position)
        if (cartItem != null) {
            holder.txtNamaMenu.text = cartItem.nama
            holder.editTextQuantity.setText(cartItem.jumlah.toString())
            val photoUrl = cartItem.photoUrl
            Log.d("photo", photoUrl)
            Picasso.get().load(photoUrl).resize(300, 300).into(holder.imgMakan)
            holder.txtHarga.text = "IDR ${cartItem.harga * cartItem.jumlah}"

            holder.btnTambah.setOnClickListener {
                cartViewModel.updateQuantity(cartItem.nama, cartItem.jumlah + 1, cartItem.harga)
                holder.txtHarga.text = "IDR ${cartItem.harga * cartItem.jumlah}"
            }

            holder.btnKurang.setOnClickListener {
                val newQuantity = maxOf(0, cartItem.jumlah - 1)
                cartViewModel.updateQuantity(cartItem.nama, newQuantity, cartItem.harga)
                holder.txtHarga.text = "IDR ${cartItem.harga * cartItem.jumlah}"
            }
        }
    }

    override fun getItemCount(): Int {
        return cartList?.size ?: 0
    }

    fun updateCartList(newCartList: List<Cart>?) {
        cartList = newCartList
        notifyDataSetChanged()
    }
}