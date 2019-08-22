package com.example.repositoriesandroid.network

import com.example.repositoriesandroid.model.Repository
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface RepositoryService {

@GET("/orgs/{org}/repos")
fun getRepositories (
    @Path ("org") organization : String
): Call<List<Repository>>

    companion object {
        private const val URL = "https://api.github.com"
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build();

        fun create(): RepositoryService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(okHttpClient)
                .build().create(RepositoryService::class.java)
        }
    }



}