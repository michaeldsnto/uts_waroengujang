package com.example.uts_waroengujang.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_waroengujang.model.Menu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val menuLD = MutableLiveData<ArrayList<Menu>>()
    val menuDetailLD = MutableLiveData<Menu>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(menuId:String?) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/menu.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Menu>>() {}.type
                val result = Gson().fromJson<List<Menu>>(it, sType)
                menuLD.value = result as ArrayList<Menu>?
                val menuList = menuLD.value
                if (menuList != null) {
                    for (menu in menuList) {
                        if (menu.id == menuId) {
                            menuDetailLD.value = menu
                        }
                    }
                }
            },
            {
                Log.e("MenuViewModel", it.toString())
            }
        )
        stringRequest.retryPolicy = DefaultRetryPolicy(
            6000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}