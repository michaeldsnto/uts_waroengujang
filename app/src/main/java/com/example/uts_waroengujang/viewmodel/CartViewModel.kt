package com.example.uts_waroengujang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uts_waroengujang.model.Cart
import com.example.uts_waroengujang.model.Menu

class CartViewModel: ViewModel() {
    val cartLD = MutableLiveData<ArrayList<Cart>?>()

    fun tambahBarang(namaMenu: String, jumlah: Int, harga: Int, photoUrl: String) {
        val cartList = cartLD.value
        cartList?.add(Cart(namaMenu, jumlah, harga, photoUrl))
        cartLD.value = cartList
    }

    fun addMenuToCart(namaMenu: String, jumlah: Int, harga: Int, photoUrl:String) {
        val cartList = cartLD.value ?: ArrayList()
        val existingItem = cartList.find { it.nama == namaMenu }
        if (existingItem != null) {
            existingItem.jumlah += jumlah
            Log.d("cart", existingItem.photoUrl)
        } else {
            cartList.add(Cart(namaMenu, jumlah, harga, photoUrl))
        }
        cartLD.value = cartList
    }
    fun updateQuantity(namaMenu: String, newQuantity: Int, harga:Int) {
        val cartList = cartLD.value ?: return
        val updatedCartList = ArrayList<Cart>()
        for (menu in cartList) {
            if (menu.nama == namaMenu) {
                if (newQuantity > 0) {
                    menu.jumlah = newQuantity
                    menu.harga*newQuantity
                    updatedCartList.add(menu)
                }
            } else {
                updatedCartList.add(menu)
            }
        }
        cartLD.value = updatedCartList
    }
}
