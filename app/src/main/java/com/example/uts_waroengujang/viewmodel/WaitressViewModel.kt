package com.example.uts_waroengujang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaitressViewModel : ViewModel() {
    private val waitressName = MutableLiveData<String>()
    private val waitressPhoto = MutableLiveData<String>()

    fun getWaitressName(): LiveData<String> {
        return waitressName
    }

    fun setWaitressName(name: String) {
        waitressName.value = name
    }

    fun getWaitressPhoto(): LiveData<String> {
        return waitressPhoto
    }

    fun setWaitressPhoto(photoUrl: String) {
        waitressPhoto.value = photoUrl
    }
}