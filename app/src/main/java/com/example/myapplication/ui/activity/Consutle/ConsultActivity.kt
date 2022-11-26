package com.example.myapplication.ui.activity.Consutle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.ConsutleList
import com.example.myapplication.network.LawyerList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_consult.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultActivity : AppCompatActivity() {
    val consutle = arrayOf("默认排序","已完成","受理中","未受理")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult)

        supportActionBar?.hide()
        qq.setOnClickListener { finish() }
        qq.setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)

        val Sp:Spinner = findViewById(R.id.sp_consu)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,consutle)
        Sp.adapter = adapter
        Sp.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (consutle[position] == "已完成"){
                    ServiceCreate.smartCityService.getConsutleList("2").enqueue(object :Callback<ConsutleList>{
                        override fun onFailure(p0: Call<ConsutleList>, p1: Throwable) {
                        }

                        override fun onResponse(
                            p0: Call<ConsutleList>,
                            p1: Response<ConsutleList>
                        ) {
                            val body = p1.body()
                            if (body != null){
                                val adapter = ItemAdapter(R.layout.item_costu,body.rows,QQ::class.java)
                                rv_consu.layoutManager= LinearLayoutManager(this@ConsultActivity)
                                rv_consu.adapter = adapter
                            }
                        }

                    })
                }else  if (consutle[position] == "受理中"){
                    ServiceCreate.smartCityService.getConsutleList("1").enqueue(object :Callback<ConsutleList>{
                        override fun onFailure(p0: Call<ConsutleList>, p1: Throwable) {
                        }

                        override fun onResponse(
                            p0: Call<ConsutleList>,
                            p1: Response<ConsutleList>
                        ) {
                            val body = p1.body()
                            if (body != null){
                                val adapter = ItemAdapter(R.layout.item_costu,body.rows,QQ::class.java)
                                rv_consu.layoutManager= LinearLayoutManager(this@ConsultActivity)
                                rv_consu.adapter = adapter
                            }
                        }

                    })
                }else  if (consutle[position] == "未受理"){
                    ServiceCreate.smartCityService.getConsutleList("0").enqueue(object :Callback<ConsutleList>{
                        override fun onFailure(p0: Call<ConsutleList>, p1: Throwable) {
                        }

                        override fun onResponse(
                            p0: Call<ConsutleList>,
                            p1: Response<ConsutleList>
                        ) {
                            val body = p1.body()
                            if (body != null){
                                val adapter = ItemAdapter(R.layout.item_costu,body.rows,QQ::class.java)
                                rv_consu.layoutManager= LinearLayoutManager(this@ConsultActivity)
                                rv_consu.adapter = adapter
                            }
                        }

                    })
                }else{
                    ServiceCreate.smartCityService.getConsutleList(null).enqueue(object :Callback<ConsutleList>{
                        override fun onFailure(p0: Call<ConsutleList>, p1: Throwable) {
                        }

                        override fun onResponse(
                            p0: Call<ConsutleList>,
                            p1: Response<ConsutleList>
                        ) {
                            val body = p1.body()
                            if (body != null){
                                val adapter = ItemAdapter(R.layout.item_costu,body.rows,QQ::class.java)
                                rv_consu.layoutManager= LinearLayoutManager(this@ConsultActivity)
                                rv_consu.adapter = adapter
                            }
                        }

                    })
                }
            }

        }

    }
}

class QQ(view: View):ItemAdapter.MyViewHolder(view){
    val nicheng:TextView = view.findViewById(R.id.nicheng99)
    val zhuangchang:TextView = view.findViewById(R.id.zhuanchang99)
    val zhuangtai:TextView = view.findViewById(R.id.zhuangtai99)
    val shijan:TextView = view.findViewById(R.id.time99)
    val image:ImageView = view.findViewById(R.id.image99)
//    val image1 = sharedPreferences.getString("image","")
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as ConsutleList.Row).imageUrls).into(image)
        nicheng.text = (data[position] as ConsutleList.Row).lawyerName
        zhuangchang.text = (data[position] as ConsutleList.Row).legalExpertiseName
        zhuangtai.text = if ((data[position] as ConsutleList.Row).state == "0"){
            "未受理"
        }else if ((data[position] as ConsutleList.Row).state == "1"){
            "受理中"
        }else{
            "已完成"
        }
        shijan.text = (data[position] as ConsutleList.Row).createTime

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,ConsultContentActivity::class.java).apply {
                putExtra("con_id",(data[position] as ConsutleList.Row).id)
            })
           "${ (data[position] as ConsutleList.Row).id}".showToast()
        }
    }
}