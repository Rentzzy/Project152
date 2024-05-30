package com.example.project152

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ListViewCartAdapter(private val listCart: List<dataCart>, private val context: Context) : BaseAdapter() {
    private lateinit var helper: SQLiteHelper

    override fun getCount(): Int {
        return listCart.size
    }

    override fun getItem(position: Int): Any {
        return listCart[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_order, null)

        val od_brand: TextView = v.findViewById(R.id.od_brand)
        val od_type: TextView = v.findViewById(R.id.od_type)
        val od_sex: TextView = v.findViewById(R.id.od_sex)
        val od_size: TextView = v.findViewById(R.id.od_size)
        val od_price: TextView = v.findViewById(R.id.od_price)
        val linear: LinearLayout = v.findViewById(R.id.linear)
        val hapus: ImageView = v.findViewById(R.id.ic_delete)

        helper = SQLiteHelper(context)

        od_brand.text = "Brand : ${listCart[position].brand}"
        od_type.text = "Type : ${listCart[position].type}"
        od_sex.text = "Sex : ${listCart[position].sex}"
        od_size.text = "Size : ${listCart[position].size}"
        od_price.text = "Price : ${listCart[position].price}"

        linear.setOnLongClickListener {
            val it = Intent(context, FormData::class.java)
            it.putExtra("ID", listCart[position].id)
            it.putExtra("BRAND", listCart[position].brand)
            it.putExtra("TYPE", listCart[position].type)
            it.putExtra("SEX", listCart[position].sex)
            it.putExtra("SIZE", listCart[position].size)
            it.putExtra("PRICE", listCart[position].price)
            it.putExtra("TANDA", "Ubah")
            context.startActivity(it)
            true
        }

        hapus.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.setMessage("Apakah anda ingin menhapus item ini?")
                .setPositiveButton("YA") { dialogInterface, _ ->
                    val isDelete = helper.deleteData(listCart[position].id)
                    if (isDelete > 0) {
                        Toast.makeText(context, "Data Successfully Deleted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Data Not Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("TIDAK") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
            alertDialog.show()
        }

        return v
    }
}