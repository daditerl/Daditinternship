package com.example.daditinternship

import RecyclerViewAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    private val propertyList: MutableList<Listing> = mutableListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi dan set layout manager pada RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_apart).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // Buat instance dari RecyclerViewAdapter dan set ke RecyclerView
        adapter = RecyclerViewAdapter(propertyList, this) // Pass propertyList
        recyclerView.adapter = adapter

        // Ambil data dari API
        fetchPropertyData()
    }

    override fun onItemClick(apartment: Listing) {
        // Respond to item click here (e.g., navigate to a detail page)
        val intent = Intent(this, DetailApartment::class.java)
        intent.putExtra("apartment_id", apartment.id) // Pass ID atau informasi lain
        startActivity(intent)
    }

    private fun fetchPropertyData() {
        val call = apiService.getListings() // Memanggil API listings.json
        call.enqueue(object : Callback<List<Listing>> {
            override fun onResponse(call: Call<List<Listing>>, response: Response<List<Listing>>) {
                if (response.isSuccessful) {
                    val propertyData = response.body()
                    propertyData?.let {
                        // Tambahkan data yang diterima dari API ke dalam MutableList
                        propertyList.clear() // Kosongkan list jika perlu
                        propertyList.addAll(it)

                        // Notifikasi adapter bahwa data telah berubah
                        adapter.notifyDataSetChanged()

                        // Tampilkan jumlah data
                        Toast.makeText(this@MainActivity, "Data berhasil ditambahkan: ${propertyList.size}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API Error", "Response unsuccessful")
                }
            }

            override fun onFailure(call: Call<List<Listing>>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data", t)
            }
        })
    }
}

//Fungsi Utama Kode
//
//    Mengambil data properti dari API Firebase yang di-hosting dan
//    menampilkannya dalam RecyclerView di tampilan utama aplikasi.
//    Menyediakan navigasi ke halaman detail properti ketika item dalam daftar
//    diklik oleh pengguna.\
//Kode ini mengilustrasikan integrasi Retrofit untuk mengambil data dari API,
// pengaturan RecyclerView
// untuk menampilkan data, dan penerapan fungsi klik untuk berinteraksi dengan data tersebut.
