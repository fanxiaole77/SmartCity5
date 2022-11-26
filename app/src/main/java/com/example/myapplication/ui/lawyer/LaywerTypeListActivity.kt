package com.example.myapplication.ui.lawyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.LawyerList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_laywer_type_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaywerTypeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laywer_type_list)

        supportActionBar?.hide()
        val id = intent.getIntExtra("LawyerTypeId",0)

        ServiceCreate.smartCityService.getLawyerList2(id).enqueue(object :Callback<LawyerList>{
            override fun onFailure(p0: Call<LawyerList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerList>, p1: Response<LawyerList>) {
                val body = p1.body()
                if (body != null){
                    val adapter =    ItemAdapter(R.layout.item_lawyer, body.rows, NN::class.java)
                    rv_lawyer_type.layoutManager = LinearLayoutManager(this@LaywerTypeListActivity)
                    rv_lawyer_type.adapter = adapter
                }
            }

        })

    }
}