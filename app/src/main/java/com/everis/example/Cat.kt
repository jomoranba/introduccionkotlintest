package com.everis.example

import kotlinx.android.parcel.Parcelize

@Parcelize
class Cat(override val id: Int, override val breed: String, override val gender: Gender) :Pet(id, breed, gender) {

}