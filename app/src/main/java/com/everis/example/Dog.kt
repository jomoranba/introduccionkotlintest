package com.everis.example

import android.os.Parcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class Dog(override val id: Int, override val breed: String, override val gender: Gender) :Pet(id, breed, gender) {
}