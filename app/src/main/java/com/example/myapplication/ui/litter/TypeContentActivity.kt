package com.example.myapplication.ui.litter

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.LitterType
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_type_content.*
import kotlinx.android.synthetic.main.fragment_litter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_content)

        window.statusBarColor = Color.YELLOW
        supportActionBar?.hide()

        o.setOnClickListener { finish() }
        o.setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)

        val id = intent.getIntExtra("littertypeid",0)
        ServiceCreate.smartCityService.getLitterType(id).enqueue(object :Callback<LitterType>{
            override fun onFailure(p0: Call<LitterType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterType>, p1: Response<LitterType>) {
                val body = p1.body()
                if (body != null){
                    for (a in body.rows){
                        if (a.id == id){
                            Glide.with(litter_type_image).load(ServiceCreate.url + a.imgUrl).into(litter_type_image)
                            litter_type_title.text = a.introduce
                            litter_type_content.text = Html.fromHtml(a.guide)
                        }
                    }
                }
            }

        })
    }
}