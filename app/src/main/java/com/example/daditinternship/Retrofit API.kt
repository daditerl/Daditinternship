package com.example.daditinternship

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Endpoint untuk mengambil data dari details/0.json
    @GET("details/{id}.json")
    fun getPropertyData(@Path("id") id: Int): Call<listDetail>

    // Endpoint untuk mengambil data dari listings.json
    @GET("listings.json")
    fun getListings(): Call<List<Listing>>

}
// umtuk mengambil file JSON dari API
