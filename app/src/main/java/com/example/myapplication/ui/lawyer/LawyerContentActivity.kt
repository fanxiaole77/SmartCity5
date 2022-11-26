package com.example.myapplication.ui.lawyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.LawyerContent
import com.example.myapplication.network.ServiceCreate
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_lawyer_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LawyerContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_content)
        supportActionBar?.hide()

        val id = sharedPreferences.getString("lawid","").toInt()
        Log.d("lawid",id.toString())

        val adapter = LawyerPagerAdapter(supportFragmentManager)
        v3.adapter = adapter

        ServiceCreate.smartCityService.getLawyerContent(id).enqueue(object :Callback<LawyerContent>{
            override fun onFailure(p0: Call<LawyerContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerContent>, p1: Response<LawyerContent>) {
                val body = p1.body()
                if (body != null){
                    name.text = body.data.name
                    zhunachang1.text = body.data.legalExpertiseName
                    renshu1.text = body.data.serviceTimes.toString()
                    Glide.with(this@LawyerContentActivity).load(ServiceCreate.url+body.data.avatarUrl).into(image1)
                }
            }

        })

    }
}