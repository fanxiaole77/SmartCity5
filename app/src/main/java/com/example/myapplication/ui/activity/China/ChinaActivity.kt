package com.example.myapplication.ui.activity.China

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_china.*
import java.util.*

class ChinaActivity : AppCompatActivity() {

    private val array = arrayListOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3
    )

    private var ChinaBanner:Banner<Int,BannerImageAdapter<Int>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_china)

        supportActionBar?.hide()
        ChinaBanner = findViewById(R.id.china_banner)

        btn_changshang.setOnClickListener {
            startActivity(Intent(this,VendorActivity::class.java))
        }

        ChinaBanner?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(this@ChinaActivity).load(array[p2]).into(p0!!.imageView)
                }

            })
        }

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video03}")
        video1.setVideoURI(uri)
        video1.start()

        val uri1 = Uri.parse("android.resource://$packageName/${R.raw.video03}")
        video2.setVideoURI(uri1)
        video2.start()
    }
}