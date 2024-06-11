package com.example.project152

import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class Cart : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ListViewCartAdapter
    private val listCart = ArrayList<dataCart>()
    private lateinit var helper: SQLiteHelper
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)

        listView = findViewById(R.id.cart_list_view)
        refreshLayout = findViewById(R.id.refresh)

        refreshLayout.setOnRefreshListener {
            menampilkanData()
        }

        helper = SQLiteHelper(this)

        menampilkanData()
    }

    private fun menampilkanData() {
        listCart.clear()
        val res: Cursor = helper.getDataAll()
        refreshLayout.isRefreshing = true
        while (res.moveToNext()) {
            val id: String = res.getString(0)
            val brand: String = res.getString(1)
            val type: String = res.getString(2)
            val sex: String = res.getString(3)
            val size: String = res.getString(4)
            val price: String = res.getString(5)
            val location: String = res.getString(6) // Ambil lokasi dari Cursor

            listCart.add(dataCart(id, brand, type, sex, size, price, location)) // Tambahkan lokasi ke dataCart
        }
        adapter = ListViewCartAdapter(listCart, this@Cart)
        listView.adapter = adapter
        refreshLayout.isRefreshing = false
    }
}