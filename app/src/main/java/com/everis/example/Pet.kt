package com.everis.example

import android.os.Parcelable

abstract class Pet(open val id: Int, open val breed: String, open val gender: Gender):Parcelable {

    lateinit var name: String

    fun adopt(name: String) {
        this.name = name
    }
}