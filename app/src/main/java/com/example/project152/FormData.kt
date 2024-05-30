package com.example.project152

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormData : AppCompatActivity() {
    private lateinit var psnBrand: EditText
    private lateinit var psnType: EditText
    private lateinit var psnSex: EditText
    private lateinit var psnPrice: EditText
    private lateinit var psnSize: EditText
    private lateinit var buttonConfirm: Button
    private lateinit var helper: SQLiteHelper
    private var pilih = "Tambah"
    private var id: String? = null
    private var brand: String? = null
    private var type: String? = null
    private var sex: String? = null
    private var size: String? = null
    private var price: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_data)

        psnBrand = findViewById(R.id.psnBrand)
        psnType = findViewById(R.id.psnType)
        psnSex = findViewById(R.id.psnSex)
        psnPrice = findViewById(R.id.psnPrice)
        psnSize = findViewById(R.id.psnSize)
        buttonConfirm = findViewById(R.id.confirmData)

        helper = SQLiteHelper(this)

        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("ID")
            brand = bundle.getString("BRAND")
            type = bundle.getString("TYPE")
            sex = bundle.getString("SEX")
            size = bundle.getString("SIZE")
            price = bundle.getString("PRICE")
            pilih = bundle.getString("TANDA") ?: "Tambah"

            psnBrand.setText(brand)
            psnType.setText(type)
            psnSex.setText(sex)
            psnPrice.setText(price)
            psnSize.setText(size)
        }

        buttonConfirm.setOnClickListener {
            val brand = psnBrand.text.toString()
            val type = psnType.text.toString()
            val sex = psnSex.text.toString()
            val price = psnPrice.text.toString()
            val size = psnSize.text.toString()

            when {
                TextUtils.isEmpty(brand) -> {
                    psnBrand.error = "Must be filled"
                    psnBrand.requestFocus()
                }
                TextUtils.isEmpty(type) -> {
                    psnType.error = "Must be filled"
                    psnType.requestFocus()
                }
                TextUtils.isEmpty(sex) -> {
                    psnSex.error = "Must be filled"
                    psnSex.requestFocus()
                }
                TextUtils.isEmpty(price) -> {
                    psnPrice.error = "Must be filled"
                    psnPrice.requestFocus()
                }
                TextUtils.isEmpty(size) -> {
                    psnSize.error = "Must be filled"
                    psnSize.requestFocus()
                }
                else -> {
                    if (pilih == "Tambah") {
                        val isInsert = helper.insertData(
                            brand,
                            type,
                            sex,
                            price,
                            size
                        )

                        if (isInsert) {
                            Toast.makeText(this@FormData, "Data Saved", Toast.LENGTH_LONG).show()
                            kosong()
                            startActivity(Intent(this@FormData, Cart::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@FormData, "Data is not saved", Toast.LENGTH_SHORT).show()
                            kosong()
                            startActivity(Intent(this@FormData, Cart::class.java))
                            finish()
                        }
                    } else {
                        val isUpdate = id?.let { nonNullId ->
                            helper.updateData(nonNullId, brand ?: "", type ?: "", sex ?: "", price ?: "", size ?: "")
                        } ?: false


                        if (isUpdate) {
                            Toast.makeText(this@FormData, "Data Succesfully Changed", Toast.LENGTH_LONG).show()
                            kosong()
                            startActivity(Intent(this@FormData, Cart::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@FormData, "Data is not saved", Toast.LENGTH_SHORT).show()
                            kosong()
                            startActivity(Intent(this@FormData, Cart::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun kosong() {
        psnBrand.setText("")
        psnType.setText("")
        psnSex.setText("")
        psnPrice.setText("")
        psnSize.setText("")
    }
}