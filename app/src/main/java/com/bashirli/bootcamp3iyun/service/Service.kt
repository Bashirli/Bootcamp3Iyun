package com.bashirli.bootcamp3iyun.service

import com.bashirli.bootcamp3iyun.model.product.ProductResponse
import com.bashirli.bootcamp3iyun.model.token.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Service {

    @POST("auth/login")
    @FormUrlEncoded
    fun userLogin(@Field("username") username:String,@Field("password") password:String):Call<TokenResponse>

    @GET("products")
    fun getProducts(@Query("limit") limit:Int,
                    @Query("sort") sort:String) : Call<ProductResponse>


}