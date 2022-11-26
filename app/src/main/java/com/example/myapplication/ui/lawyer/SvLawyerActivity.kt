package com.example.myapplication.ui.lawyer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.LawyerList
import com.example.myapplication.network.LawyerTen
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_sv_lawyer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SvLawyerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_lawyer)
        supportActionBar?.hide()
        a.setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)
        a.setOnClickListener { finish() }

        sv_lawyer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                ServiceCreate.smartCityService.getLawyerList(newText)
                    .enqueue(object : Callback<LawyerList> {
                        override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
                        }

                        override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                            val body = p1.body()
                            if (body != null) {
                                val adapter =
                                    ItemAdapter(R.layout.item_lawyer, body.rows, NN::class.java)
                                rv_lawyer.layoutManager = LinearLayoutManager(this@SvLawyerActivity)
                                rv_lawyer.adapter = adapter
                            }
                        }

                    })
                return false
            }

        })
    }
}

class NN(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.mingzi)
    val nianxian: TextView = view.findViewById(R.id.nianxian)
    val renshu: TextView = view.findViewById(R.id.renshu)
    val zhuanchang: TextView = view.findViewById(R.id.zhuanchang)
    val haoping: TextView = view.findViewById(R.id.haoping)
    val btn: Button = view.findViewById(R.id.zixun)
    val image: ImageView = view.findViewById(R.id.touxiang)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LawyerList.Row).name
        renshu.text = (data[position] as LawyerList.Row).serviceTimes.toString()
        nianxian.text = (data[position] as LawyerList.Row).workStartAt
        zhuanchang.text = (data[position] as LawyerList.Row).legalExpertiseName
        haoping.text = (data[position] as LawyerList.Row).favorableRate.toString()
        Glide.with(image).load(ServiceCreate.url + (data[position] as LawyerList.Row).avatarUrl)
            .into(image)
        btn.setOnClickListener {
            sharedPreferences.edit {
                putString("lawid", (data[position] as LawyerList.Row).id.toString())
            }
            itemView.context.startActivity(Intent(itemView.context, LawyerContentActivity::class.java))
        }


    }
}