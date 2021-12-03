package com.everis.example

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    private val pet by lazy {
        intent.getParcelableExtra("pet") as Pet?
    }

    companion object {
        fun newInstance(activity: Activity, pet: Pet): Intent {
            return Intent(activity, DetailActivity::class.java).putExtra("pet", pet)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val etPetName = findViewById<EditText>(R.id.etName)

        findViewById<EditText>(R.id.etBreed).setText(pet?.breed ?: "")
        findViewById<EditText>(R.id.etGender).setText(pet?.gender.toString())

        val button = findViewById<Button>(R.id.btnAdopt)

        button.setOnClickListener {
            pet?.adopt(etPetName.text.toString())
            showMessage(pet)
        }


        //TODO : Implementar el ClickListener del botón para adoptar y asignar el nombre a la mascota, luego utilizar la funcion showMesaage para mostrar un mensaje de exito

    }

    private fun showMessage(pet: Pet?) {
        Toast.makeText(this, "Gracias por adoptar a ${pet?.name} - propietario: Marisol Montañez", Toast.LENGTH_LONG).show()
    }
}