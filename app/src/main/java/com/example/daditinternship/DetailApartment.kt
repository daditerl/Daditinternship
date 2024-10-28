package com.example.daditinternship

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide

class DetailApartment : AppCompatActivity() {

    val propertyList: MutableList<listDetail> = mutableListOf() // ini untuk menampung data" nya
    private lateinit var apartmentHargadetail: TextView
    private lateinit var apartmentTitledetail: TextView
    private lateinit var apartmentSubtitle: TextView
    private lateinit var apartmentSubtitle2: TextView
    private lateinit var apartmentImage: ImageView
    private lateinit var apartmentbeddetail: TextView
    private lateinit var apartmentkmrmandidetail: TextView
    private lateinit var apartmentluasdetail: TextView
    private lateinit var apartmentpriceorsqrt: TextView
    private lateinit var apartmentpriceorsqrt2: TextView
    private lateinit var apartmentfloorlevel: TextView
    private lateinit var apartmentfloorlevel2: TextView
    private lateinit var apartmentfurmish: TextView
    private lateinit var apartmentfurmish2: TextView
    private lateinit var apartmentfacing: TextView
    private lateinit var apartmentfacing2: TextView
    private lateinit var apartmentoverlooking: TextView
    private lateinit var apartmentoverlooking2: TextView
    private lateinit var apartmentbuilt: TextView
    private lateinit var apartmentbuilt2: TextView
    private lateinit var apartmenttenure: TextView
    private lateinit var apartmenttenure2: TextView
    private lateinit var apartmentpropertytype: TextView
    private lateinit var apartmentpropertytype2: TextView
    private lateinit var apartmentdesc: TextView
    private lateinit var apartmentdesc2: TextView
    private lateinit var backbtn : ImageView
    private lateinit var mapsimage : ImageView
    private lateinit var maps : TextView
    var id = 0
// ini untuk mendeklarasi tampilan tv dalam xml / layout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.detail_apart)

//Metode ini dipanggil saat aktivitas dibuat.
//enableEdgeToEdge(): Mengaktifkan tampilan edge-to-edge untuk aktivitas ini.
//setContentView(R.layout.detail_apart): Mengatur layout yang akan digunakan oleh aktivitas ini.
//id: Mengambil ID properti dari intent yang dipassing ke aktivitas ini.
//Menampilkan Toast yang menunjukkan ID yang diterima.
//Menghubungkan elemen UI dengan ID yang sesuai di layout.
        id = intent.getIntExtra("apartment_id", -1)

        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()

        apartmentHargadetail = findViewById(R.id.tv_hargadetail)
        apartmentTitledetail  = findViewById(R.id.tv_titledetail)
        apartmentSubtitle  = findViewById(R.id.tv_subtitle2)
        apartmentSubtitle2  = findViewById(R.id.tv_subtitle)
        apartmentImage  = findViewById(R.id.imageView2)
        apartmentbeddetail = findViewById(R.id.tv_beddetail)
        apartmentkmrmandidetail  = findViewById(R.id.tv_kmrmandidetail)
        apartmentluasdetail = findViewById(R.id.tv_luasdetail)
        apartmentpriceorsqrt = findViewById(R.id.tv_priceorsqrt)
        apartmentpriceorsqrt2 = findViewById(R.id.tv_priceorsqrt2)
        apartmentfloorlevel = findViewById(R.id.tv_floorlevel)
        apartmentfloorlevel2= findViewById(R.id.tv_floorlevel2)
        apartmentfurmish = findViewById(R.id.tv_furmish)
        apartmentfurmish2  = findViewById(R.id.tv_furmish2)
        apartmentfacing= findViewById(R.id.tv_facing)
        apartmentfacing2 = findViewById(R.id.tv_facing2)
        apartmentoverlooking = findViewById(R.id.tv_overlooking)
        apartmentoverlooking2= findViewById(R.id.tv_overlooking2)
        apartmentbuilt= findViewById(R.id.tv_built)
        apartmentbuilt2= findViewById(R.id.tv_built2)
        apartmenttenure  = findViewById(R.id.tv_tenure)
        apartmenttenure2 = findViewById(R.id.tv_tenure2)
        apartmentpropertytype = findViewById(R.id.tv_propertyType)
        apartmentpropertytype2 = findViewById(R.id.tv_propertyType2)
        apartmentdesc= findViewById(R.id.tv_desc)
        apartmentdesc2 = findViewById(R.id.tv_desc2)
        maps = findViewById(R.id.mapsTV)
        mapsimage = findViewById(R.id.mapsIMG)

        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener{
            finish()
        }

        fetchPropertyData()
//ini untuk maps dengan bisa di set onclik agar bisa di clik lalu ke maps
        maps.setOnClickListener {
            // Ganti dengan latitude dan longitude lokasi yang ingin dibuka
            val latitude = propertyList[0].address.map_coordinates.lat // Contoh: Jakarta
            val longitude = propertyList[0].address.map_coordinates.lng // Contoh: Jakarta

            // Membuka Google Maps dengan lokasi tertentu
            val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps") // Pastikan untuk membuka Google Maps
            startActivity(intent)
        }




    }

// Metode ini menggunakan Retrofit untuk mengambil data properti dari API dengan ID yang telah ditentukan.
//Menggunakan callback untuk menangani respons dari API.
//Jika respons berhasil (response.isSuccessful), data properti ditambahkan ke propertyList dan UI diperbarui dengan data tersebut.
//Menggunakan Glide untuk memuat gambar ke dalam ImageView.

    fun fetchPropertyData() { //utk mengambil data" dari API lalu ke variable
        val call = apiService.getPropertyData(id)

        call.enqueue(object : Callback<listDetail> {
            override fun onResponse(call: Call<listDetail>, response: Response<listDetail>) {
                if (response.isSuccessful) {
                    val propertyData = response.body()
                    propertyData?.let {

                        propertyList.add(it)

                        if (propertyList.isNotEmpty()) {
                            Toast.makeText(this@DetailApartment, "Data berhasil ditambahkan: ${propertyList.size}", Toast.LENGTH_SHORT).show()
                            apartmentHargadetail.text = "$"+String.format("%,d", propertyList[0].attributes.price)
                            apartmentTitledetail.text = propertyList[0].project_name
                            apartmentSubtitle2.text = propertyList[0].address.subtitle
                            apartmentSubtitle.text = propertyList[0].address.title
                            apartmentbeddetail.text = propertyList[0].attributes.bedrooms.toString() + " Beds"
                            apartmentkmrmandidetail.text = propertyList[0].attributes.bathrooms.toString() +" Bath"
                            apartmentluasdetail.text = propertyList[0].attributes.area_size.toString() + " sqft"
                            apartmentpriceorsqrt.text = propertyList[0].property_details[0].label
                            apartmentpriceorsqrt2.text = propertyList[0].property_details[0].text
                            apartmentfloorlevel.text = propertyList[0].property_details[1].label
                            apartmentfloorlevel2.text = propertyList[0].property_details[1].text
                            apartmentfurmish.text = propertyList[0].property_details[2].label
                            apartmentfurmish2.text = propertyList[0].property_details[2].text
                            apartmentfacing.text = propertyList[0].property_details[3].label
                            apartmentfacing2.text = propertyList[0].property_details[3].text
                            apartmentoverlooking.text = propertyList[0].property_details[4].label
                            apartmentoverlooking2.text = propertyList[0].property_details[4].text
                            apartmentbuilt.text = propertyList[0].property_details[5].label
                            apartmentbuilt2.text = propertyList[0].property_details[5].text
                            apartmenttenure.text = propertyList[0].property_details[6].label
                            apartmenttenure2.text = propertyList[0].property_details[6].text
                            apartmentpropertytype.text = propertyList[0].property_details[7].label
                            apartmentpropertytype2.text = propertyList[0].property_details[7].text
                            apartmentdesc2.text = propertyList[0].description

                            Glide.with(this@DetailApartment)
                                .load(propertyList[0].photo)  // URL gambar dari API
//                                .placeholder(R.drawable.placeholder)  // Gambar placeholder saat loading
//                                .error(R.drawable.error)  // Gambar saat gagal load
                                .into(apartmentImage)  // ImageView tempat gambar ditampilkan
                        }
                    }
                } else {
                    Log.e("API Error", "Response unsuccessful")
                }
            }

            override fun onFailure(call: Call<listDetail>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data", t)
            }
        })
    }

}

//Secara keseluruhan, kode ini mengimplementasikan tampilan
// detail apartemen dalam aplikasi Android dengan pengambilan
// data dari API dan pengelolaan UI secara dinamis. Kode ini menggunakan
// beberapa konsep penting dalam pengembangan aplikasi Android, termasuk
// pemrograman berbasis objek, interaksi dengan API, dan pengelolaan UI.



