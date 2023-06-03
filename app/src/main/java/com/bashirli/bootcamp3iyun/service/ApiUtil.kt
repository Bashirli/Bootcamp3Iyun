package com.bashirli.bootcamp3iyun.service

import com.bashirli.bootcamp3iyun.util.BASE_URL
import retrofit2.Retrofit

class ApiUtil {
    companion object{
        fun getService():Service{
            return RetrofitUtil.getRetrofit(BASE_URL).create(Service::class.java)
        }
    }
}