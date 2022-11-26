package com.example.myapplication.ui.litter

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import com.example.myapplication.ui.activity.`fun`.FunActivity
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_litter_sv.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LitterSvActivity : AppCompatActivity() {

    var text:Int = 0
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_litter_sv)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        u.setOnClickListener { finish() }
        u.setCollapseIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)


        ServiceCreate.smartCityService.getBannerType().enqueue(object :Callback<LitterTypeBanner>{
            override fun onFailure(p0: Call<LitterTypeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterTypeBanner>, p1: Response<LitterTypeBanner>) {
                val body = p1.body()
                if (body != null){
                    latter_banner_type.adapter = object :BannerImageAdapter<LitterTypeBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: LitterTypeBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }

                    }
                }
            }
        })


        sv_litter.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText)){
                    rv_hot_title.visibility = View.GONE
                }else{
                    rv_hot_title.visibility = View.VISIBLE
                }

                if (newText == "可回收垃圾"){
                    text = 8
                }else if (newText == "有害垃圾"){
                    text = 9
                }else if (newText == "厨余垃圾"){
                    text = 10
                }else if (newText == "其他垃圾"){
                    text = 11
                }else{
                    "没有当前分类".showToast()
                }

                ServiceCreate.smartCityService.getLitterList(text).enqueue(object :Callback<LitterList>{
                    override fun onFailure(p0: Call<LitterList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<LitterList>, p1: Response<LitterList>) {
                        val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_service,body.rows,GG::class.java)
                            rv_litter.layoutManager = GridLayoutManager(this@LitterSvActivity,4)
                            rv_litter.adapter = adapter
                        }
                    }

                })
                return false
            }

        })

        ServiceCreate.smartCityService.getLitterHot().enqueue(object :Callback<LitterHot>{
            override fun onFailure(p0: Call<LitterHot>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterHot>, p1: Response<LitterHot>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_litter_hot,body.data,HH::class.java)
                    rv_hot_title.layoutManager = GridLayoutManager(this@LitterSvActivity,5)
                    rv_hot_title.adapter = adapter
                }
            }

        })
        ServiceCreate.smartCityService.getLitterType(8).enqueue(object :Callback<LitterType>{
            override fun onFailure(p0: Call<LitterType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterType>, p1: Response<LitterType>) {
                val body = p1.body()
                if (body != null){
                    for (a in body.rows){
                        if (a.id == 8){
                            litter_guide.text = a.guide
                        }
                    }
                }
            }

        })
    }
}

class GG(view: View): ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LitterList.Row).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LitterList.Row).imgUrl).into(image)

    }
}

class HH(view: View):ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.tv_litter_hot)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LitterHot.Data).keyword
    }

}