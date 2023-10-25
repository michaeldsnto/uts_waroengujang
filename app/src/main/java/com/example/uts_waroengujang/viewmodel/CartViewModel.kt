package com.example.uts_waroengujang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uts_waroengujang.model.Cart
import com.example.uts_waroengujang.model.Menu

class CartViewModel: ViewModel() {
    val cartLD = MutableLiveData<ArrayList<Cart>?>()

    fun tambahBarang(namaMenu: String, jumlah: Int, harga: Int) {
        val cartList = cartLD.value
        cartList?.add(Cart(namaMenu, jumlah, harga))
        cartLD.value = cartList
    }
    fun getCartList(): MutableLiveData<ArrayList<Cart>?> {
        return cartLD
    }
    fun kurangBarang(namaMenu: String) {
        val carList = cartLD.value
        if (carList != null) {
            for (menu in carList) {
                if (menu.nama == namaMenu) {
                    if (menu.jumlah < 1) {
                        carList.remove(menu)
                    }
                    else {
                        menu.jumlah -= 1
                        cartLD.value = carList
                    }
                }
            }
        }
    }
    fun hapusCart() {
        cartLD.value = ArrayList()
    }
}