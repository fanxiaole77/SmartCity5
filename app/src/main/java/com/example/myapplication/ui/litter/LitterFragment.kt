package com.example.myapplication.ui.litter

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.network.LitterBanner
import com.example.myapplication.network.LitterNewsType
import com.example.myapplication.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.fragment_litter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LitterFragment : Fragment() {
    /**
     * 垃圾分类
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_litter, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPause() {
        super.onPause()
        activity!!.window.statusBarColor = Color.TRANSPARENT
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.window.statusBarColor = Color.YELLOW
        q.setOnClickListener {
            startActivity(Intent(activity,MainActivity::class.java))
        }
        q.setCollapseIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)

        litter.setOnClickListener {
            startActivity(Intent(activity,LitterSvActivity::class.java))
        }
        type.setOnClickListener {
            startActivity(Intent(activity,TypeActivity::class.java))
        }

        ServiceCreate.smartCityService.getLitterBanner().enqueue(object :Callback<LitterBanner>{
            override fun onFailure(p0: Call<LitterBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterBanner>, p1: Response<LitterBanner>) {
                val body =p1.body()
                if (body != null){
                    litter_banner.adapter = object :BannerImageAdapter<LitterBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: LitterBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }

                    }
                }
            }

        })
        ServiceCreate.smartCityService.getLitterNewsType().enqueue(object :Callback<LitterNewsType>{
            override fun onFailure(p0: Call<LitterNewsType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterNewsType>, p1: Response<LitterNewsType>) {
               val body = p1.body()
                if (body != null){
                    val adapter = PagerLitterNewsAdapter(this@LitterFragment.requireFragmentManager(),body.rows)
                    v2.adapter = adapter
                    t2.setupWithViewPager(v2)
                }
            }

        })

    }


}