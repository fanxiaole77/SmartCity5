package com.example.myapplication.ui.litter

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.HomeService
import com.example.myapplication.network.LitterType
import com.example.myapplication.network.LitterTypeBanner
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_type.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        i.setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)
        i.setOnClickListener { finish() }

        ServiceCreate.smartCityService.getBannerType().enqueue(object :Callback<LitterTypeBanner>{
            override fun onFailure(p0: Call<LitterTypeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterTypeBanner>, p1: Response<LitterTypeBanner>) {
                val body = p1.body()
                if (body != null){
                    Glide.with(this@TypeActivity).load(ServiceCreate.url + body.data[1].imgUrl).into(image12)
                }
            }

        })

        ServiceCreate.smartCityService.getLitterType(null).enqueue(object:Callback<LitterType>{
            override fun onFailure(p0: Call<LitterType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterType>, p1: Response<LitterType>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,JJ::class.java)
                   rv_litter_type.layoutManager = GridLayoutManager(this@TypeActivity,4)
                    rv_litter_type.adapter= adapter

                }
            }

        })

    }
}


class JJ(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LitterType.Row).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LitterType.Row).imgUrl)
            .into(image)

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,TypeContentActivity::class.java).apply {
                putExtra("littertypeid",(data[position] as LitterType.Row).id)
            })
        }
    }
}

