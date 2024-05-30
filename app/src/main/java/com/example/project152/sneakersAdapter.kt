package com.example.project152

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class sneakersAdapter (private val listSepatu: ArrayList<sneakersclass>) : RecyclerView.Adapter<sneakersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val holder = ViewHolder(inflater.inflate(R.layout.item_sneakers, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sneakersAdapter: sneakersclass = listSepatu[position]
        holder.tvMerk.text = sneakersAdapter.merk
        holder.tvJenis.text = sneakersAdapter.jenis
        holder.tvHarga.text = sneakersAdapter.harga
        holder.img.setImageResource(sneakersAdapter.gambar)

        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, detailSneakers::class.java)
            intent.putExtra("nama_merk", sneakersAdapter.merk)
            intent.putExtra("nama_jenis", sneakersAdapter.jenis)
            intent.putExtra("nama_price", sneakersAdapter.harga)
            intent.putExtra("nama_desc", sneakersAdapter.desc)
            intent.putExtra("foto_sepatu", sneakersAdapter.gambar)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listSepatu.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMerk: TextView = itemView.findViewById(R.id.merk_id)
        var tvJenis: TextView = itemView.findViewById(R.id.jenis_id)
        var tvHarga: TextView = itemView.findViewById(R.id.price_id)
        var img: ImageView = itemView.findViewById(R.id.gambar_id)
    }
}