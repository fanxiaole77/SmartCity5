package com.example.myapplication.ui.lawyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.LawyerComment
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_lawyer_tab2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LawyerTab2Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lawyer_tab2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = sharedPreferences.getString("lawid","").toInt()

        ServiceCreate.smartCityService.getLawyerComment(id).enqueue(object :Callback<LawyerComment>{
            override fun onFailure(p0: Call<LawyerComment>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LawyerComment>, p1: Response<LawyerComment>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_lawyer_comment,body.rows,OO::class.java)
                    rv_tab2.layoutManager  = LinearLayoutManager(this@LawyerTab2Fragment.requireActivity())
                    rv_tab2.adapter = adapter
                }
            }

        })
    }
}
class OO(view: View):ItemAdapter.MyViewHolder(view){
    val nicheng:TextView = view.findViewById(R.id.nicheng11)
    val shijian:TextView = view.findViewById(R.id.shijian11)
    val nierong:TextView = view.findViewById(R.id.neirong11)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        nicheng.text = (data[position] as LawyerComment.Row).fromUserName
        shijian.text = (data[position] as LawyerComment.Row).createTime
        nierong.text = (data[position] as LawyerComment.Row).evaluateContent
    }
}