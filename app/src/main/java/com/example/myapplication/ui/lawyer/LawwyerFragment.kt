package com.example.myapplication.ui.lawyer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import com.example.myapplication.ui.activity.Consutle.ConsultActivity
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.fragment_lawwyer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LawyerFragment : Fragment() {
    /**
     * 律师主页
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lawwyer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lawyer_sv.setOnClickListener {
            startActivity(Intent(activity, SvLawyerActivity::class.java))
        }

        cak.setOnClickListener {
            startActivity(Intent(activity, LawyerListActivity::class.java))
        }


        ServiceCreate.smartCityService.getLawyerBanner().enqueue(object : Callback<LawyerBanner> {
            override fun onFailure(p0: Call<LawyerBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerBanner>, p1: Response<LawyerBanner>) {
                val body = p1.body()
                if (body != null) {
                    lawyerBanner.adapter =
                        object : BannerImageAdapter<LawyerBanner.Data>(body.data) {
                            override fun onBindView(
                                p0: BannerImageHolder?,
                                p1: LawyerBanner.Data?,
                                p2: Int,
                                p3: Int
                            ) {
                                Glide.with(this@LawyerFragment)
                                    .load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                            }

                        }
                }
            }

        })

        ServiceCreate.smartCityService.getLawyer().enqueue(object : Callback<LawyerExper> {
            override fun onFailure(p0: Call<LawyerExper>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerExper>, p1: Response<LawyerExper>) {
                val body = p1.body()
                if (body != null) {
                    val adapter = ItemAdapter(R.layout.item_service, body.rows, KK::class.java, 8)
                    lawyer_Mear.layoutManager =
                        GridLayoutManager(this@LawyerFragment.requireActivity(), 4)
                    lawyer_Mear.adapter = adapter
                }
            }
        })

        ServiceCreate.smartCityService.getMyLawyer().enqueue(object : Callback<MyLawyerList> {
            override fun onFailure(p0: Call<MyLawyerList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<MyLawyerList>, p1: Response<MyLawyerList>) {
                val body = p1.body()
                if (body != null) {
                    val adapter = ItemAdapter(R.layout.item_image, body.rows, LL::class.java)
                    rv_image.layoutManager =
                        GridLayoutManager(this@LawyerFragment.requireActivity(), 2)
                    rv_image.adapter = adapter
                }
            }

        })

        ServiceCreate.smartCityService.getLawyerTen().enqueue(object : Callback<LawyerTen> {
            override fun onFailure(p0: Call<LawyerTen>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerTen>, p1: Response<LawyerTen>) {
                val body = p1.body()
                if (body != null) {

//                    val arrayrenshu = mutableSetOf<String>()
//                    val arrayhaoping = mutableSetOf<String>()
//                    val arrayimage = mutableSetOf<String>()

                    val adapter = ItemAdapter(
                        R.layout.item_lawyer,
                        body.data,
                        MM::class.java
                    )
                    rv_ten.layoutManager =
                        LinearLayoutManager(this@LawyerFragment.requireActivity())
                    rv_ten.adapter = adapter
//                    sharedPreferences.edit {
//                        putStringSet("renshu",arrayrenshu)
//                        putStringSet("haoping",arrayhaoping)
//                        putStringSet("image",arrayimage)
//                    }
                }
            }

        })
    }
}


class KK(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LawyerExper.Row).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LawyerExper.Row).imgUrl)
            .into(image)

        itemView.setOnClickListener {
            itemView.context.startActivity(
                Intent(
                    itemView.context,
                    LaywerTypeListActivity::class.java
                ).apply {
                    putExtra("LawyerTypeId", (data[position] as LawyerExper.Row).id)
                })
        }
    }
}

class LL(view: View) : ItemAdapter.MyViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.lawyer_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as MyLawyerList.Row).imageUrls)
            .into(image)
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context, ConsultActivity::class.java))
        }
    }
}

class MM(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.mingzi)
    val nianxian: TextView = view.findViewById(R.id.nianxian)
    val renshu: TextView = view.findViewById(R.id.renshu)
    val zhuanchang: TextView = view.findViewById(R.id.zhuanchang)
    val haoping: TextView = view.findViewById(R.id.haoping)
    val btn: Button = view.findViewById(R.id.zixun)
    val image: ImageView = view.findViewById(R.id.touxiang)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        name.text = (data[position] as LawyerTen.Data).name
        renshu.text = (data[position] as LawyerTen.Data).serviceTimes.toString()
        nianxian.text = (data[position] as LawyerTen.Data).workStartAt
        zhuanchang.text = (data[position] as LawyerTen.Data).legalExpertiseName
        haoping.text = (data[position] as LawyerTen.Data).favorableRate.toString()
        Glide.with(image).load(ServiceCreate.url + (data[position] as LawyerTen.Data).avatarUrl)
            .into(image)
//        val arrayrenshu = mutableListOf<String>()
//        val arrayhaoping = mutableListOf<String>()
//        val arrayimage = mutableListOf<String>()
////
//        val Info0 = data[0] as MutableSet<String>
//        val Info1 = data[1] as MutableSet<String>
//        val Info2 = data[2] as MutableSet<String>

//        Info0.add((data[position] as LawyerTen.Data).serviceTimes.toString())
//        Info1.add((data[position] as LawyerTen.Data).favorableRate.toString())
//        Info2.add((data[position] as LawyerTen.Data).avatarUrl)

        btn.setOnClickListener {
            "${(data[position] as LawyerTen.Data).id}".showToast()
            sharedPreferences.edit {
                putString("lawid", (data[position] as LawyerTen.Data).id.toString())
            }

            itemView.context.startActivity(
                Intent(
                    itemView.context,
                    LawyerContentActivity::class.java
                )
            )
        }


    }
}