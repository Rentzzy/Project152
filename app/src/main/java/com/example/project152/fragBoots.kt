package com.example.project152

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class fragBoots : Fragment() {
    private lateinit var v: View
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var listSepatu: MutableList<sneakersclass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_frag_sneakers, container, false)
        myRecyclerView = v.findViewById(R.id.sneakers_rcy)
        initData()
        myRecyclerView.layoutManager = LinearLayoutManager(activity)

        // Initialize adapter
        val adapter = sneakersAdapter(ArrayList(listSepatu)) // Add ArrayList() here
        myRecyclerView.adapter = adapter
        return v
    }

    private fun initData() {
        listSepatu = mutableListOf()
        listSepatu.add(
            sneakersclass(
                "Eiger",
                "Mid-boots",
                "Rp 500.000",
                "Mid-cut boot with a simple and classic design that is suitable for daily use or traveling use. " +
                        "keep your feet dry from water splashes, " +
                        "prevents small objects like pebbles from entering when you take a leisurely stroll around town.",
                R.drawable.boots1
            )
        )
        listSepatu.add(
            sneakersclass(
                "Brodo",
                "Vintage Boots",
                "Rp 750.000",
                "Elegan di Setiap Lekuknya " +
                        "A combination of the minimalist design of moccasin shoes and Brodo's signature line, " +
                        "It doesn't get wet easily and is effective in keeping your feet dry.",
                R.drawable.boots2
            )
        )
        listSepatu.add(
            sneakersclass(
                "Comforto",
                "Low-Boots",
                "Rp 400.000",
                "Your Daily Comfort Boots " +
                        "a combination of simple and elegant Loafers with thick soles with a height of 3.5 cm, " +
                        "ready to support your gentleman appearance. ",
                R.drawable.boots3
            )
        )
    }
}