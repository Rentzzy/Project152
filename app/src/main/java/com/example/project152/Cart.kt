package com.example.project152

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class Cart : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ListViewCartAdapter
    private val listCart = ArrayList<dataCart>()
    private lateinit var helper: SQLiteHelper
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var locationEditText: EditText

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)

        listView = findViewById(R.id.cart_list_view)
        refreshLayout = findViewById(R.id.refresh)
        locationEditText = this.findViewById(R.id.psnLocation)

        refreshLayout.setOnRefreshListener {
            menampilkanData()
        }

        helper = SQLiteHelper(this)
        locationClient = LocationServices.getFusedLocationProviderClient(this)

        setupLocationRequest()
        setupLocationCallback()

        checkLocationPermission()

        menampilkanData()
    }

    private fun setupLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = (1000 * 30).toLong()
            fastestInterval = (1000 * 5).toLong()
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let { location ->
                    updateLocationUI(location)
                }
            }
        }
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

            listCart.add(dataCart(id, brand, type, sex, size, price))
        }
        adapter = ListViewCartAdapter(listCart, this@Cart)
        listView.adapter = adapter
        refreshLayout.isRefreshing = false
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            getLocation()
        }
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    private fun updateLocationUI(location: Location) {
        val locationText = "Lat: ${location.latitude}, Long: ${location.longitude}"
        locationEditText.setText(locationText)

        val geocoder = Geocoder(this)
        try {
            val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1) ?: emptyList()
            if (addresses.isNotEmpty()) {
                val address = addresses[0].getAddressLine(0)
                locationEditText.append("\n$address")
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to get street address", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}