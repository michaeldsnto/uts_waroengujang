package com.example.uts_waroengujang.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.model.Menu
import com.squareup.picasso.Picasso

class MenuAdapter(private val menuList: ArrayList<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNamaMenu: TextView
        val txtHarga: TextView
        val photoMenu: ImageView
        init {
            txtNamaMenu = view.findViewById(R.id.txtNamaMenu)
            txtHarga = view.findViewById(R.id.txtHarga)
            photoMenu = view.findViewById(R.id.photoMenu)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.txtNamaMenu.text = menuList[position].nama
        holder.txtHarga.text = "IDR " + menuList[position].harga
        val photoUrl = menuList[position].photoUrl
        Picasso.get().load(photoUrl).resize(300, 300).into(holder.photoMenu)
        val menuId = menuList[position].id

        holder.photoMenu.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuDetail(menuId.toString())
            Navigation.findNavController(it).navigate(action)
        }
        holder.txtNamaMenu.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuDetail(menuId.toString())
            Navigation.findNavController(it).navigate(action)
        }
        holder.txtHarga.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuDetail(menuId.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun updateMenuList(newMenuList: ArrayList<Menu>){
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }

}