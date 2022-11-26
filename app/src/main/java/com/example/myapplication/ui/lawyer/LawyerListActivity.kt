package com.example.myapplication.ui.lawyer

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.LawyerList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_lawyer_list.*
import kotlinx.android.synthetic.main.activity_news_content.*
import kotlinx.android.synthetic.main.activity_sv_lawyer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LawyerListActivity : AppCompatActivity(),View.OnClickListener{
    val players = arrayOf("默认排序","好评率","从业年限","服务人数")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_list)

        supportActionBar?.hide()

        btn_screen.setOnClickListener(this)

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,players)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("players","${players[position]}")
                if (players[position] == "好评率"){
                    ServiceCreate.smartCityService.getLawyerList1("favorableRate").enqueue(object :Callback<LawyerList>{
                        override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
                        }

                        override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                           val body = p1.body()
                            if (body!= null){
                                val adapter = ItemAdapter(R.layout.item_lawyer,body.rows,NN::class.java)
                                rv_lawyer_list.layoutManager = LinearLayoutManager(this@LawyerListActivity)
                                rv_lawyer_list.adapter = adapter
                            }
                        }

                    })
                }else if (players[position] == "服务人数"){
                    ServiceCreate.smartCityService.getLawyerList1("serviceTimes").enqueue(object :Callback<LawyerList>{
                        override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
                        }

                        override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                            val body = p1.body()
                            if (body!= null){
                                val adapter = ItemAdapter(R.layout.item_lawyer,body.rows,NN::class.java)
                                rv_lawyer_list.layoutManager = LinearLayoutManager(this@LawyerListActivity)
                                rv_lawyer_list.adapter = adapter
                            }
                        }

                    })
                }else if (players[position] == "从业年限"){
                    ServiceCreate.smartCityService.getLawyerList1("workStartAt").enqueue(object :Callback<LawyerList>{
                        override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
                        }

                        override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                            val body = p1.body()
                            if (body!= null){
                                val adapter = ItemAdapter(R.layout.item_lawyer,body.rows,NN::class.java)
                                rv_lawyer_list.layoutManager = LinearLayoutManager(this@LawyerListActivity)
                                rv_lawyer_list.adapter = adapter
                            }
                        }

                    })
                }else if (players[position] == "默认排序"){
                    ServiceCreate.smartCityService.getLawyerList1(null).enqueue(object :Callback<LawyerList>{
                        override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
                        }

                        override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                            val body = p1.body()
                            if (body!= null){
                                val adapter = ItemAdapter(R.layout.item_lawyer,body.rows,NN::class.java)
                                rv_lawyer_list.layoutManager = LinearLayoutManager(this@LawyerListActivity)
                                rv_lawyer_list.adapter = adapter
                            }
                        }

                    })
                }
            }
        }



    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_screen -> {
                val screen = ScreenActivity(this)
                screen.apply {
                    showAtLocation(v,Gravity.TOP,width/2,0)
                }
            }
        }
    }
}