package io.github.defolters.taskdistribution.data.remote

import com.google.gson.GsonBuilder
import io.github.defolters.taskdistribution.data.remote.interfaces.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private const val ENDPOINT = "http://192.168.1.110:8080/v1/"

    private val okClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val gson = GsonBuilder().setLenient().create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okClient)
            .build()
    }

    var apiService: ApiService = retrofit.create(
        ApiService::class.java
    )
}