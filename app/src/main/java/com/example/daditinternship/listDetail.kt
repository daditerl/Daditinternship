package com.example.daditinternship

// pada code ini menyambung ke dalam DetailApartment.kt dan ke retrofit


data class Coordinates(
    val lat: Double,
    val lng: Double
)
//Data class ini menyimpan informasi mengenai koordinat lokasi dengan dua properti:

data class FullAddress(
    val map_coordinates: Coordinates,
    val subtitle: String,
    val title: String
)
// Data class ini menyimpan informasi alamat lengkap. Memiliki tiga properti:

data class FullAttributes(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int
)

data class PropertyDetail(
    val label: String,
    val text: String
)
data class listDetail(
    val address: FullAddress,
    val attributes: FullAttributes,
    val description: String,
    val id: Int,
    val photo: String,
    val project_name: String,
    val property_details: List<PropertyDetail>
)

//Kode ini mendefinisikan struktur data yang diperlukan
// untuk menyimpan dan mengelola informasi tentang properti
// dalam aplikasi. Dengan menggunakan data class, Anda dapat
// dengan mudah membuat objek yang merepresentasikan properti,
// termasuk detail alamat, atribut, dan informasi tambahan lainnya.
// Ini memungkinkan kode menjadi lebih terorganisir dan memudahkan
// pengelolaan data ketika mengambil informasi dari API atau sumber lainnya.



