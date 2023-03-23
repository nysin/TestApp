package com.test.testapp.net.base

import android.util.Log
import com.test.testapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {

    private val TIME_OUT = 30L
    private val okHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(TIME_OUT,TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT,TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
//            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {msg->
//            Log.d("http",msg)
//
//            }))
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = if (BuildConfig.DEBUG) {
//                    HttpLoggingInterceptor.Level.BODY
//                }else {
//                    HttpLoggingInterceptor.Level.BASIC
//                }
//            })
            .build()
    }

    fun <T> getService(service:Class<T>,baseUrl:String) :T {
       return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
    }
}