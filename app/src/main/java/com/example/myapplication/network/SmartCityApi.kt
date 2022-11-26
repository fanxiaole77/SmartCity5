package com.example.myapplication.network

import com.example.myapplication.SmartCityApplication
import retrofit2.Call
import retrofit2.http.*

interface SmartCityApi {

    @POST("/prod-api/api/login")
    fun postLogin(@Body user:Login):Call<Message>


    @POST("/prod-api/api/register")
    fun postRegister(@Body user:Register):Call<Message>

    @GET("/prod-api/api/rotation/list")
    fun getHomeBanner():Call<HomeBanner>

    @GET("/prod-api/api/service/list")
    fun getHomeService():Call<HomeService>

    @GET("/prod-api/press/category/list")
    fun getNewsType():Call<NewsType>

    @GET("/prod-api/press/press/list")
    fun getHot(@Query("hot") hot:String = "Y"):Call<NewsLIst>

    @GET("/prod-api/press/press/list")
    fun getNewsList(@Query("type")type:String?,@Query("title")title:String?):Call<NewsLIst>

    @GET("/prod-api/press/press/{id}")
    fun getNewsContent(@Path("id")id:Int):Call<NewsContent>

    @GET("/prod-api/api/common/user/getInfo")
    fun getUserInfo(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<UserInfo>

    @GET("/prod-api/api/garbage-classification/news-type/list")
    fun getLitterNewsType(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterNewsType>

    @GET("/prod-api/api/garbage-classification/news/list")
    fun getLitterNewsList(@Query("type")type:String?, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterNewsList>

    @GET("/prod-api/api/garbage-classification/ad-banner/list")
    fun getLitterBanner(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterBanner>


    @GET("prod-api/api/garbage-classification/news/{id}")
    fun getLitterNewsContent(@Path("id")id: Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterNewContent>

    @GET("/prod-api/api/garbage-classification/news-comment/list")
    fun getLitterNewsCommentList(@Query("newsId")newsId:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterNewsCommentList>

    @POST("/prod-api/api/garbage-classification/news-comment")
    fun postLitterComment(@Body comment:LitterNewsComment,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/garbage-classification/garbage-classification/list")
    fun getLitterType(@Query("id")id: Int?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterType>

    @GET("prod-api/api/garbage-classification/garbage-example/list")
    fun getLitterList(@Query("type")type:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterList>

    @GET("/prod-api/api/garbage-classification/poster/list")
    fun getBannerType(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterTypeBanner>

    @GET("/prod-api/api/garbage-classification/garbage-classification/hot/list")
    fun getLitterHot(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LitterHot>

    @GET("/prod-api/api/lawyer-consultation/lawyer/list")
    fun getLawyerList(@Query("name")name:String?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerList>

    @GET("/prod-api/api/lawyer-consultation/lawyer/{id}")
    fun getLawyerContent(@Path("id")id:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerContent>

    @GET("/prod-api/api/lawyer-consultation/ad-banner/list")
    fun getLawyerBanner(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerBanner>

    @GET("/prod-api/api/lawyer-consultation/legal-expertise/list")
    fun getLawyer(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerExper>

    @GET("/prod-api/api/lawyer-consultation/legal-advice/list")
    fun getMyLawyer(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<MyLawyerList>

    @GET("/prod-api/api/lawyer-consultation/lawyer/list-top10")
    fun getLawyerTen(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerTen>

    @GET("/prod-api/api/lawyer-consultation/lawyer/list")
    fun getLawyerList1(@Query("sort")sort:String?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerList>

    @GET("/prod-api/api/lawyer-consultation/lawyer/list-evaluate")
    fun getLawyerComment(@Query("lawyerId")lawyerId:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerComment>

    @GET("/prod-api/api/lawyer-consultation/lawyer/list")
    fun getLawyerList2(@Query("legalExpertiseId")legalExpertiseId:Int?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LawyerList>

    @GET("/prod-api/api/lawyer-consultation/legal-advice/list")
    fun getConsutleList(@Query("state")state:String?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ConsutleList>

    @GET("/prod-api/api/lawyer-consultation/legal-advice/{id}")
    fun getConsutleListContent(@Path("id")id:Int, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ConsutleListContent>

    @POST("/prod-api/api/lawyer-consultation/legal-advice")
    fun postConsutle(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>


    @POST("/prod-api/api/lawyer-consultation/legal-advice/evaluate")
    fun postCom(@Body com:Com, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

}