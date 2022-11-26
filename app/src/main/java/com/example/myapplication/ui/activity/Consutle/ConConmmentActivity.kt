package com.example.myapplication.ui.activity.Consutle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import com.example.myapplication.R
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Com
import com.example.myapplication.network.Message
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_con_conmment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ConConmmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_conmment)

        supportActionBar?.hide()
        val id = intent.getIntExtra("com_id",0)



        xingxing.setOnRatingBarChangeListener(object :RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                btn_fabu.setOnClickListener {
                   if (rating.toInt() < 1){
                       "至少一颗星际".showToast()
                   }else{
                       val neirong = et_neirong.text.toString()
                       ServiceCreate.smartCityService.postCom(Com(neirong,id,rating.toInt())).enqueue(object :Callback<Message>{
                           override fun onFailure(p0: Call<Message>, p1: Throwable) {
                           }

                           override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                               val body = p1.body()
                               if (body != null){
                                   if (body.code == 200){
                                       body.msg.showToast()
                                   }else{
                                       body.msg.showToast()
                                   }
                               }
                           }

                       })
                   }
                }
            }
        })



    }
}