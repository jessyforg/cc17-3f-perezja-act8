package com.example.cc17_3f_perezja_act8.models

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val descriptor: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String
)
