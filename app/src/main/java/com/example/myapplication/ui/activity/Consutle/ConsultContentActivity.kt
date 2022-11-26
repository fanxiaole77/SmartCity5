package com.example.myapplication.ui.activity.Consutle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.ConsutleListContent
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_consult_content.*
import kotlinx.android.synthetic.main.activity_news_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ConsultContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_content)
        supportActionBar?.hide()

        ww.setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)
        ww.setOnClickListener { finish() }

        val id = intent.getIntExtra("con_id",0)

        Log.d("con_id",id.toString())

        val renshu = sharedPreferences.getStringSet("renshu",setOf())
        val haoping = sharedPreferences.getStringSet("haoping", setOf())
        val image = sharedPreferences.getStringSet("image",setOf())

        Log.d("renshu",renshu.size.toString())


        ServiceCreate.smartCityService.getConsutleListContent(id).enqueue(object :Callback<ConsutleListContent>{
            override fun onFailure(p0: Call<ConsutleListContent>, p1: Throwable) {
            }

            override fun onResponse(
                p0: Call<ConsutleListContent>,
                p1: Response<ConsutleListContent>
            ) {
                val body = p1.body()
                if (body != null){

                    nicheng55.text = body.data.lawyerName
                    nianxian55.text = body.data.createTime
//                    renshu55.text = renshu
                    zhuangtai55.text =if ( body.data.state == "0"){
                        "未受理"
                    }else if (body.data.state == "1"){
                        "受理中"
                    }else{
                        "已完成"
                    }
//                    haoping55.text = haoping
                    zhuanchang55.text = body.data.legalExpertiseName.toString()
                    leixing55.text = body.data.lawyerName
                    miaoshu55.text = body.data.content
                    dianhua55.text = body.data.phone
                    Glide.with(this@ConsultContentActivity).load(ServiceCreate.url + image).into(image55)

                    if (body.data.state == "0"){
                        btn_comment.visibility = View.GONE
                    }else if (body.data.state == "1"){
                        btn_comment.visibility = View.GONE
                    }else{
                        btn_comment.visibility = View.VISIBLE
                    }

                    btn_comment.setOnClickListener {
                        startActivity(Intent(this@ConsultContentActivity,ConConmmentActivity::class.java).apply {
                            putExtra("com_id",body.data.id)
                        })
                    }

                }
            }
        })
    }
}