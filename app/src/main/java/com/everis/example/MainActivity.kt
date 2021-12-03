package com.everis.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Lista de mascotas"

        val rvPetList = findViewById<RecyclerView>(R.id.rvPetList)

        val adapter = PetAdapter()

        //TODO: Crear las clases Dog y Cat que extiendan de Pet (no modificar la clase Pet)

        val petList = listOf(
            Dog(1, "Shiba", Gender.MALE),
            Cat(2, "Siámes", Gender.FEMALE),
            Dog(3, "Pastor Aléman", Gender.MALE),
            Cat(4, "Persa", Gender.MALE),
            Dog(5, "Labrador", Gender.FEMALE),
            Dog(6, "Labrador", Gender.MALE),
            Dog(7, "Shiba", Gender.FEMALE),
        )

        adapter.submitPets(petList)

        adapter.setClickItemCallback {
            startActivity(DetailActivity.newInstance(this, it))
        }

        rvPetList.adapter = adapter
    }
}