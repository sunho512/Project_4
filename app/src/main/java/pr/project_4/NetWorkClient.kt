package com.android.a11

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pr.project_4.BuildConfig
import pr.project_4.NetWorkInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {

    private const val Search_URL = "https://dapi.kakao.com/v2/search/image"


    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

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

}object Constants {

    // Kakao Image Search API의 기본 URL입니다.
    const val Search_URL = "https://dapi.kakao.com"

    // Kakao API를 사용하기 위한 인증 헤더입니다.
    const val AUTH_HEADER = "KakaoAK 7581f80aa0c7a862f5cd0af5865d9511"

    // 앱의 Shared Preferences 파일 이름입니다.
    const val PREFS_NAME = "com.jblee.imagesearch.prefs"

    // 마지막 검색어를 저장하기 위한 키 값입니다.
    const val PREF_KEY = "IMAGE_SEARCH_PREF"
}