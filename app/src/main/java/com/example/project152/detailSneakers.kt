package com.example.project152

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detailSneakers : AppCompatActivity() {

    private lateinit var detMerk: TextView
    private lateinit var detJenis: TextView
    private lateinit var detPrice: TextView
    private lateinit var detDesc: TextView
    private lateinit var detGambar: ImageView

    private var namaMerk: String = ""
    private var namaJenis: String = ""
    private var namaPrice: String = ""
    private var namaDesc: String = ""
    private var fotoSepatu: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_sneakers)

        detMerk = findViewById(R.id.det_merk)
        detJenis = findViewById(R.id.det_jenis)
        detPrice = findViewById(R.id.price_id)
        detDesc = findViewById(R.id.desc)
        detGambar = findViewById(R.id.det_img)

        //foto pakaian
        val bundle: Bundle? = intent.extras
        fotoSepatu = bundle?.getInt("foto_sepatu") ?: 0
        detGambar.setImageResource(fotoSepatu)

        getInputExtra()
    }

    private fun getInputExtra() {
        namaMerk = intent.getStringExtra("nama_merk") ?: ""
        namaJenis = intent.getStringExtra("nama_jenis") ?: ""
        namaPrice = intent.getStringExtra("nama_price") ?: ""
        namaDesc = intent.getStringExtra("nama_desc") ?: ""

        setDataActivity(namaMerk, namaJenis, namaPrice, namaDesc)
    }

    private fun setDataActivity(namaMerk: String, namaJenis: String, namaPrice: String, namaDesc: String) {
        detMerk.text = namaMerk
        detJenis.text = namaJenis
        detPrice.text = namaPrice
        detDesc.text = namaDesc
    }

    fun addCart(view: View) {
        val intent = Intent(this, FormData::class.java)
        startActivity(intent)
    }
}