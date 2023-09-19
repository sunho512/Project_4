package com.android.a11

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pr.project_4.NetWorkInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {

    private const val Search_URL = "https://dapi.kakao.com/v2/search/image"


    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val SearchRetrofit = Retrofit.Builder()
        .baseUrl(Search_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    val SearchNetWork: NetWorkInterface = SearchRetrofit.create(NetWorkInterface::class.java)

}