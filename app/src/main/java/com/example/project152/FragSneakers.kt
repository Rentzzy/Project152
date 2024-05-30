package com.example.project152

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragSneakers : Fragment() {
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
                "Nike",
                "Air Max 77",
                "Rp 1.500.000",
                "A sneakers is a running, Featuring the original ripple design inspired by Japanese bullet trains " +
                        "the Nike Air Max 97 lets you push your style full-speed ahead" +
                        " Taking the revolutionary full-length Nike Air unit that shook up the running world and adding fresh colours and crisp details",
                R.drawable.sneakers1
            )
        )
        listSepatu.add(
            sneakersclass(
                "Adidas",
                "Samba",
                "Rp 2.300.000",
                "A sneakers is a basic, Inspired by Inter Miami CF's away colours " +
                        "these limited-collection adidas Samba football boots include a tonal club badge on the tongue." +
                        " The supple leather upper will ensure you stay comfortable, whether you take them onto the futsal court or wear them beyond the touchline.",
                R.drawable.sneakers3
            )
        )
        listSepatu.add(
            sneakersclass(
                "Converse",
                "Chuck 70s",
                "Rp 1.200.000",
                "A sneakers is a vulcanized, Durable canvas upper for that classic Chuck 70 look and feel " +
                        "Vintage-inspired design elements like an egret midsole, ornate stitching, and a taller rubber sidewall" +
                        " tried-and-true design, while modern-comfort updates bring your look into the present.",
                R.drawable.sneakers2
            )
        )
        listSepatu.add(
            sneakersclass(
                "Nike",
                "Air Jordan 4",
                "Rp 3.329.000",
                "Step into a classic. The beloved AJ4 returns with a little extra shine. " +
                        "Its pristine white leather upper features Oxidised Green accents," +
                        " bringing a metallic lustre to the eyelets, midsole and branding. ",
                R.drawable.sneakers4
            )
        )
        listSepatu.add(
            sneakersclass(
                "Asics",
                "GT-2160",
                "Rp 1.899.000",
                "A sneakers is a Running, retaining key features such as the sleek aesthetic and sculpted sculpted forefoot. " +
                        "Other influential details from the 2010s include a segmented midsole structure and GEL™ technology inserts." +
                        " This combination creates an industrial aesthetic combined with advanced absorption properties. ",
                R.drawable.sneakers5
            )
        )
        listSepatu.add(
            sneakersclass(
                "New Balance",
                "2002R",
                "Rp 1.999.200 ",
                "A sneakers is a Running, proves that the slick kick is still comfortable." +
                        "Leather/mesh upper inspired by running shoes from the 2000s for a retro-meets-modern aesthetic," +
                        " ABZORB midsole and ABZORB SBS heel cushioning provide seamless comfort. ",
                R.drawable.sneakers6
            )
        )
        listSepatu.add(
            sneakersclass(
                "Onitsuka Tiger",
                "Serrano",
                "Rp 2,600,000 ",
                "A sneakers is a Running, These Onitsuka Tiger Serrano shoes blend the performance of the track with the style of the streets." +
                        "With its lightweight nylon upper, streamlined silhouette and spiked rubber outsole," +
                        " this stylish sports shoe takes inspiration from the high-powered track spikes. ",
                R.drawable.sneakers7
            )
        )
        listSepatu.add(
            sneakersclass(
                "Vans",
                "Old Skool",
                "Rp 800,000 ",
                "The Iconic Shoe that Brought our Sidestripe to Life: This is the Old Skool." +
                        "This low-top silhouette has established itself as an icon in the skate, music, and fashion scenes, " +
                        " the Old Skool has consistently been the go-to shoe for creatives who do things their own way. ",
                R.drawable.sneakers8
            )
        )
    }
}
