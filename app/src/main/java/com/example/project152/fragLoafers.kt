package com.example.project152

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class fragLoafers : Fragment() {
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
        listSepatu = ArrayList()
        listSepatu.add(
            sneakersclass(
                "Dr-Martens",
                "3 eye shoe",
                "Rp 2.799.000",
                "A towering platform. With everyday wearability." +
                        "  cutting-edge lightweight construction. " +
                        "Nappa Lux - a classic nappa leather — soft and smooth to the touch.",
                R.drawable.loafers1
            )
        )
        listSepatu.add(
            sneakersclass(
                "Dr-Martens",
                "Adrian Tassel",
                "Rp 2.999.000",
                "Smooth Leather upper and iconic slip-on design." +
                        " The first Docs shoe inspired by subcultures, " +
                        "durable, with a smooth and polished finish.",
                R.drawable.loafers2
            )
        )
        listSepatu.add(
            sneakersclass(
                "Dr-Martens",
                "Icons 1461",
                "Rp 2.799.000",
                "Built from our traditional, durable Smooth leather." +
                        " Fixed to a classic air-cushion sole with our signature Goodyear welt yellow stitches. " +
                        "Looking back and stepping forwards.",
                R.drawable.loafers3
            )
        )
    }
}