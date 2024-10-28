package com.example.daditinternship

data class Listing(
    val address: Address,
    val attributes: Attributes,
    val category: String,
    val completed_at: Int,
    val id: Int,
    val photo: String,
    val project_name: String,
    val tenure: Int,

)

data class Address(
    val district: String,
    val street_name: String
)

data class Attributes(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int,
)

//Fungsi Kode Ini
//
//Kode ini digunakan untuk merepresentasikan
// detail dari suatu properti. Dengan mendefinisikan struktur data Listing,
// Address, dan Attributes, aplikasi dapat menyimpan, mengakses, dan mengelola
// informasi properti dengan lebih mudah dan terstruktur. Struktur ini memudahkan
// pengolahan data, baik untuk menampilkan informasi ke pengguna, menyimpan data
// ke dalam database, atau mengirimkan data melalui API.

