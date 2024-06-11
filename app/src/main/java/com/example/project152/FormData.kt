package com.example.project152

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener

class FormData : AppCompatActivity() {
    private lateinit var psnBrand: EditText
    private lateinit var psnType: EditText
    private lateinit var psnSex: EditText
    private lateinit var psnPrice: EditText
    private lateinit var psnSize: EditText
    private lateinit var psnLocation: EditText
    private lateinit var buttonConfirm: Button
    private lateinit var btnLocation: Button
    private lateinit var helper: SQLiteHelper
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var pilih = "Tambah"
    private var id: String? = null
    private var brand: String? = null
    private var type: String? = null
    private var sex: String? = null
    private var size: String? = null
    private var price: String? = null

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_data)

        psnBrand = findViewById(R.id.psnBrand)
        psnType = findViewById(R.id.psnType)
        psnSex = findViewById(R.id.psnSex)
        psnPrice = findViewById(R.id.psnPrice)
        psnSize = findViewById(R.id.psnSize)
        psnLocation = findViewById(R.id.psnLocation)
        buttonConfirm = findViewById(R.id.confirmData)
        btnLocation = findViewById(R.id.btnLocation)

        helper = SQLiteHelper(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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

        btnLocation.setOnClickListener {
            getLocationPermission()
        }

        buttonConfirm.setOnClickListener {
            val brand = psnBrand.text.toString()
            val type = psnType.text.toString()
            val sex = psnSex.text.toString()
            val price = psnPrice.text.toString()
            val size = psnSize.text.toString()
            val location = psnLocation.text.toString()

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
                            size,
                            location
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
                            helper.updateData(nonNullId, brand ?: "", type ?: "", sex ?: "", price ?: "", size ?: "", location)
                        } ?: false

                        if (isUpdate) {
                            Toast.makeText(this@FormData, "Data Successfully Changed", Toast.LENGTH_LONG).show()
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

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getDeviceLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
    }

    private fun getDeviceLocation() {
        try {
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnCompleteListener(this, OnCompleteListener<Location> { task ->
                if (task.isSuccessful) {
                    val location: Location? = task.result
                    if (location != null) {
                        psnLocation.setText("${location.latitude}, ${location.longitude}")
                    }
                } else {
                    Toast.makeText(this, "Unable to get location. Make sure location is enabled on the device.", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getDeviceLocation()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
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
        psnLocation.setText("")
    }
}