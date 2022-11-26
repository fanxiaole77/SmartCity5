package com.example.myapplication.ui.litter

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import kotlinx.android.synthetic.main.activity_litter_news_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LitterNewsContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_litter_news_content)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        t.setOnClickListener { finish() }
        t.setCollapseIcon(R.drawable.ic_baseline_keyboard_arrow_left_24)

        val id = intent.getIntExtra("litter_news_id",0)

        ServiceCreate.smartCityService.getLitterNewsContent(id).enqueue(object :Callback<LitterNewContent>{
            override fun onFailure(p0: Call<LitterNewContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LitterNewContent>, p1: Response<LitterNewContent>) {
               val body = p1.body()
                if (body != null){
                    litter_news_content_title.text = body.data.title
                    litter_news_content_time.text = body.data.createTime
                    litter_news_content_content.text = Html.fromHtml(body.data.content)
                }
            }

        })

       fun bb() = ServiceCreate.smartCityService.getLitterNewsCommentList(id).enqueue(object :Callback<LitterNewsCommentList>{
            override fun onFailure(p0: Call<LitterNewsCommentList>, p1: Throwable) {
            }

            override fun onResponse(
                p0: Call<LitterNewsCommentList>,
                p1: Response<LitterNewsCommentList>
            ) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_litter_news_comment,body.rows,FF::class.java)
                    rv_litter_news_comment_list.layoutManager = LinearLayoutManager(this@LitterNewsContentActivity)
                    rv_litter_news_comment_list.adapter = adapter
                }
            }

        })

        bb()

        btn_litter_comment.setOnClickListener {
            val commnet = et_litter_comment.text.toString()
            ServiceCreate.smartCityService.postLitterComment(LitterNewsComment(commnet,id)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                            bb()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}
class FF(view: View):ItemAdapter.MyViewHolder(view){
    val comment:TextView = view.findViewById(R.id.litter_news_comment)
    override fun binViewHolder(list: List<Any?>, position: Int, data: List<Any?>) {
        comment.text = (data[position] as LitterNewsCommentList.Row).content
    }
}