package com.example.project152

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class tablayout_custom : AppCompatActivity() {
    //variabel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: MainAdapter
    private lateinit var keranjang: ImageView

    //menampilkan halaman untuk list bajuu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout_custom)

        keranjang = findViewById(R.id.keranjang)
        keranjang.setOnClickListener {
            val intent = Intent(this@tablayout_custom, Cart::class.java)
            startActivity(intent)
        }

        //assign variable
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        //initialize adapter
        adapter = MainAdapter(supportFragmentManager)

        //tambahkan fragment
        adapter.addFragment(FragSneakers(), "Sneakers")
        adapter.addFragment(fragBoots(), "Boots")
        adapter.addFragment(fragLoafers(), "Loafers")

        //setAdapter
        viewPager.adapter = adapter

        //sambungkan tab layout dengan viewpager
        tabLayout.setupWithViewPager(viewPager)
    }

    private class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        //initiallize arraylist
        private val fragmentArrayList = ArrayList<Fragment>()
        private val stringArrayList = ArrayList<String>()

        //buat constructor
        fun addFragment(fragment: Fragment, s: String) {
            //tambahkan fragment
            fragmentArrayList.add(fragment)
            //tambahkan string
            stringArrayList.add(s)
        }

        override fun getItem(position: Int): Fragment {
            //return fragment position
            return fragmentArrayList[position]
        }

        override fun getCount(): Int {
            //return fragment list size
            return fragmentArrayList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            //rerturn tab title
            return stringArrayList[position]
        }
    }
}
