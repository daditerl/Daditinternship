package com.example.daditinternship

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://ninetyninedotco-b7299.asia-southeast1.firebasedatabase.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)

// fungsi kode
//declare link api nya
