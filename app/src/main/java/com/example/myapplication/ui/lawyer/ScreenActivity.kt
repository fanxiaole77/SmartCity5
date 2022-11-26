package com.example.myapplication.ui.lawyer

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.LawyerExper
import com.example.myapplication.network.LawyerList
import com.example.myapplication.network.LitterType
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_lawyer_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenActivity(context: Context) : PopupWindow(context) {
    init {
        val view  = LayoutInflater.from(context).inflate(R.layout.activity_screen,null,false)
        val rv  = view.findViewById<RecyclerView>(R.id.rv_screen)
        this?.apply {
            isFocusable = true
            isClippingEnabled = true
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = 1000
            contentView = view
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
        ServiceCreate.smartCityService.getLawyer().enqueue(object :Callback<LawyerExper>{
            override fun onFailure(p0: Call<LawyerExper>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerExper>, p1: Response<LawyerExper>) {
              val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,PP::class.java)
                    rv.layoutManager = GridLayoutManager(context,4)
                    rv.adapter = adapter
                }
            }

        })
    }
}


class PP(val view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LawyerExper.Row).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LawyerExper.Row).imgUrl)
            .into(image)

    }
}