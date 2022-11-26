package com.example.myapplication.ui.activity.China

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_vendor.*

class VendorActivity : AppCompatActivity() {
    private val array = arrayListOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor)

        supportActionBar?.hide()

        ven_banner.adapter = object :BannerImageAdapter<Int>(array){
            override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                Glide.with(this@VendorActivity).load(array[p2]).into(p0!!.imageView)
            }

        }

        val adapter = VonPagerAdapter(supportFragmentManager)
        v4.adapter = adapter

    }
}