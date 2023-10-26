package com.example.uts_waroengujang.model
import com.google.gson.annotations.SerializedName

data class Waitress(
    val id:String?,
    val name:String,
    val username:String,
    val password:String,
    @SerializedName("work_since")
    val workSince:String,
    @SerializedName("photo_url")
    val photoUrl:String,
)

data class Menu(
    val id:String?,
    @SerializedName("nama_makanan")
    val nama:String,
    val kategori:String,
    val harga: Int,
    val deskripsi: String,
    val photoUrl:String
)

data class Cart (
    val nama:String,
    var jumlah:Int,
    val harga:Int,
    val photoUrl:String,
)