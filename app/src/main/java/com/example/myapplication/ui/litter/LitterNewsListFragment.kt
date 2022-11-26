package com.example.myapplication.ui.litter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.LitterNewsList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_litter_news_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LitterNewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LitterNewsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_litter_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ServiceCreate.smartCityService.getLitterNewsList(param1).enqueue(object :Callback<LitterNewsList>{
            override fun onFailure(p0: Call<LitterNewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterNewsList>, p1: Response<LitterNewsList>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_litter_news_list,body.rows,EE::class.java)
                    rv_litter_news_list.layoutManager = LinearLayoutManager(this@LitterNewsListFragment.requireActivity())
                    rv_litter_news_list.adapter = adapter
                }
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LitterNewsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LitterNewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

class EE(view: View):ItemAdapter.MyViewHolder(view){
    val title:TextView = view.findViewById(R.id.litter_news_title)
    val time:TextView = view.findViewById(R.id.litter_news_time)
    val image:ImageView = view.findViewById(R.id.litter_news_image)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        title.text = (data[position] as LitterNewsList.Row).title
        time.text = (data[position] as LitterNewsList.Row).createTime
        Glide.with(image).load(ServiceCreate.url + (data[position] as LitterNewsList.Row).imgUrl).into(image)

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,LitterNewsContentActivity::class.java).apply {
                putExtra("litter_news_id",(data[position] as LitterNewsList.Row).id)
            })
        }
    }
}