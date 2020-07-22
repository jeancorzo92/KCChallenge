package com.example.kcchallenge.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

object ServiceGenerator {

    private const val BASE_URL = "https://s3.amazonaws.com/technical-challenge/v3/"

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))

    private val retrofit = builder.build()

    private val httpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    private val gson
        get() = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}