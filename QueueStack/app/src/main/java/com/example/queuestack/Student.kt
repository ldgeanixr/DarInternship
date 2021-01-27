package com.example.queuestack

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    val id: Int,
    val name: String? = "",
    val surname: String? = "",
    val grade: Double? = 0.0,
    val image: String? = ""
) : Parcelable