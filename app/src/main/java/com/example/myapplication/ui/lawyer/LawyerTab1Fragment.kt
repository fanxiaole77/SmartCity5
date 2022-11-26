package com.example.myapplication.ui.lawyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.LawyerContent
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_lawyer_content.*
import kotlinx.android.synthetic.main.fragment_lawyer_tab1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LawyerTab1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lawyer_tab1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = sharedPreferences.getString("lawid","").toInt()

        ServiceCreate.smartCityService.getLawyerContent(id).enqueue(object :Callback<LawyerContent>{
            override fun onFailure(p0: Call<LawyerContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerContent>, p1: Response<LawyerContent>) {
               val body = p1.body()
                if (body != null){
                    jianjie2.text = body.data.baseInfo
                    beijing1.text = body.data.eduInfo
                    nianxian1.text = body.data.workStartAt
                    zhenghao1.text = body.data.licenseNo
                    Glide.with(this@LawyerTab1Fragment).load(ServiceCreate.url + body.data.certificateImgUrl).into(tabimage)
                }
            }

        })

    }


}